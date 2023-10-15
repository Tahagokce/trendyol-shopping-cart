package com.trendyol.shoppingcartservice.service.impl;

import com.trendyol.common.exception.LoggedUserNotFoundException;
import com.trendyol.common.exception.RuleNotValidException;
import com.trendyol.core.util.SecurityUtil;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.card.service.CartService;
import com.trendyol.shoppingcartservice.card.util.CartUtil;
import com.trendyol.shoppingcartservice.rule.executor.AddItemValidationRuleExecutor;
import com.trendyol.shoppingcartservice.rule.executor.AddVasItemToItemValidationRuleExecutor;
import com.trendyol.shoppingcartservice.service.ShoppingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final CartService cartService;
    private final AddItemValidationRuleExecutor addItemRuleExecutorFactory;
    private final AddVasItemToItemValidationRuleExecutor addVasItemToItemValidationRuleExecutor;

    @Override
    public void addItem(CartItemDocument cartItem) {
        CartDocument cart = getCartForLoggedUser();

        // Find matching product
        Optional<CartItemDocument> matchingCartItem = findMatchingCartItem(cart, cartItem);

        if (matchingCartItem.isPresent()) {
            var currentCartItem = matchingCartItem.get();
            int newQuantity = currentCartItem.getQuantity() + cartItem.getQuantity();

            currentCartItem.setQuantity(newQuantity);
        } else {
            // Add new item
            cart.getItems().add(cartItem);
        }

        // Calculate the amount
        CartUtil.calculateAmount(cart);

        // validate
        addItemRuleExecutorFactory.validator(cart);

        // Register card
        cartService.save(cart);
    }

    @Override
    public void addVasItemToItem(VasItemDocument vasItem) {
        CartDocument cart = getCartForLoggedUser();

        // vasItem search for the item to be added
        Optional<CartItemDocument> cartItemOpt = cart.getItems().stream()
                .filter(
                        cartItem -> Objects.equals(cartItem.getItemId(), vasItem.getItemId())
                                && (Objects.equals(cartItem.getCategoryId(), 3004L)
                                    || Objects.equals(cartItem.getCategoryId(), 1001L)))
                .findFirst();

        // If there is no such item in the cart, it will not progress.
        if (cartItemOpt.isEmpty()) {
            throw new RuleNotValidException("There are no such products in the sep to add vas items.");
        }

        CartItemDocument cartItem = cartItemOpt.get();
        Optional<VasItemDocument> vasItemOpt =
                cartItem.getVasItems().stream().filter(vi -> vi.equals(vasItem)).findFirst();

        // Has the same product been added to the item before? If added, it will be updated according to the amount of that record.
        if (vasItemOpt.isPresent()){
            VasItemDocument currentVasItem = vasItemOpt.get();
            int quantity = currentVasItem.getQuantity() + vasItem.getQuantity();
            vasItem.setQuantity(quantity);

            currentVasItem.setQuantity(vasItem.getQuantity());
            currentVasItem.setPrice(vasItem.getPrice());
        }else {
            // If it is not added, a new vas item will be added.
            cartItem.getVasItems().add(vasItem);
        }

        // subjected to validation
        addVasItemToItemValidationRuleExecutor.validator(vasItem, cartItem);

        // If the verification is successful, the amount is calculated and saved.
        CartUtil.calculateAmount(cart);
        cartService.save(cart);
    }


    @Override
    public void removeItem(Long itemId) {
        CartDocument cart = getCartForLoggedUser();
        List<CartItemDocument> cartItems = cart.getItems().stream()
                .filter(ci -> !Objects.equals(ci.getItemId(), itemId)).toList();

        if (Objects.equals(cartItems.size(), cart.getItems().size())) {
            throw new RuleNotValidException();
        }

        cart.setItems(cartItems);
        CartUtil.calculateAmount(cart);
        cartService.save(cart);
    }

    @Override
    public void resetCart() {
        CartDocument cart = getCartForLoggedUser();
        Long userId = cart.getUserId();

        cart = new CartDocument();
        cart.setUserId(userId);
        cart.setId(userId);

        CartUtil.calculateAmount(cart);
        cartService.save(cart);
    }

    @Override
    public CartDocument displayCart() {
        return getCartForLoggedUser();
    }

    private CartDocument getCartForLoggedUser() {
        Long loggedUserId = SecurityUtil.getLoggedUserId().orElseThrow(LoggedUserNotFoundException::new);
        return cartService.findByUserId(loggedUserId);
    }

    private Optional<CartItemDocument> findMatchingCartItem(CartDocument cart, CartItemDocument cartItem) {
        return cart.getItems().stream().filter(ci -> ci.equals(cartItem)).findFirst();
    }
}
