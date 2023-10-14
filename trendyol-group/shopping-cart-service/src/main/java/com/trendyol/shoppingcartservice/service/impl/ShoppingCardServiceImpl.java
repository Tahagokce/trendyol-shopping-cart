package com.trendyol.shoppingcartservice.service.impl;

import com.trendyol.common.exception.LoggedUserNotFoundException;
import com.trendyol.common.exception.RuleNotValidException;
import com.trendyol.core.model.dto.AppliedPromotionDto;
import com.trendyol.core.util.SecurityUtil;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.card.rule.factory.CartRuleExecutorFactory;
import com.trendyol.shoppingcartservice.card.service.CartService;
import com.trendyol.shoppingcartservice.card.util.CartUtil;
import com.trendyol.shoppingcartservice.promotion.util.PromotionUtil;
import com.trendyol.shoppingcartservice.service.ShoppingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final CartService cartService;
    private final CartRuleExecutorFactory cartRuleExecutorFactory;

    @Override
    public void addItem(CartItemDocument cartItem){
        CartDocument cart = getCartForLoggedUser();
        cart.getItems().add(cartItem);

        if(!cartRuleExecutorFactory.isValid(cart)){
            throw new RuleNotValidException();
        }

        AppliedPromotionDto appliedPromotionDto = PromotionUtil.applyMostAdvantagePromotion(cart);

        cart.setTotalAmount(appliedPromotionDto.getTotalAmount());
        cart.setTotalDiscount(appliedPromotionDto.getTotalDiscount());
        cart.setAppliedPromotionId(appliedPromotionDto.getAppliedPromotionId());

        cartService.save(cart);
    }

    @Override
    public void addVasItemToItem(VasItemDocument vasItem){
        CartDocument cart = getCartForLoggedUser();

        if (!Objects.equals(vasItem.getSellerId(), 5003L)){
            throw new RuleNotValidException();
        }

        List<CartItemDocument> cartItems = cart.getItems()
                .stream()
                .filter(ci -> Objects.equals(ci.getItemId(), vasItem.getItemId()))
                .filter(ci -> Objects.equals(ci.getCategoryId(), 1001L)
                        || Objects.equals(ci.getCategoryId(), 3004L)
                        && (ci.getVasItems().size() < 3))
                .filter(ci -> (ci.getVasItems().stream().mapToInt(VasItemDocument::getQuantity).sum() + vasItem.getQuantity()) <= 3)
                .toList();

        if (CollectionUtils.isEmpty(cartItems)){
            throw new RuleNotValidException();

        }

        List<Long> removeIds = cartItems.stream().map(CartItemDocument::getItemId).toList();
        cartItems.forEach(ci -> ci.getVasItems().add(vasItem));

        List<CartItemDocument> newCartItems = new java.util.ArrayList<>(cart.getItems().stream()
                .filter(ci -> !removeIds.contains(ci.getItemId()))
                .toList());

        newCartItems.addAll(cartItems);


        cart.setItems(newCartItems);
        CartUtil.calculateAmount(cart);
        cartService.save(cart);
    }


    @Override
    public void removeItem(Long itemId){
        CartDocument cart = getCartForLoggedUser();
        List<CartItemDocument> cartItems = cart.getItems().stream().filter(ci -> !Objects.equals(ci.getItemId(), itemId)).toList();

        if (Objects.equals(cartItems.size(), cart.getItems().size())){
            throw new RuleNotValidException();
        }

        cart.setItems(cartItems);
        CartUtil.calculateAmount(cart);
        cartService.save(cart);
    }

    @Override
    public void resetCart(){
        CartDocument cart = getCartForLoggedUser();
        Long userId = cart.getUserId();
        cart = new CartDocument();
        cart.setUserId(userId);
        cart.setId(userId);
        cartService.save(cart);
    }

    @Override
    public CartDocument displayCart(){
        return getCartForLoggedUser();
    }

    private CartDocument getCartForLoggedUser(){
        Long loggedUserId = SecurityUtil.getLoggedUserId().orElseThrow(LoggedUserNotFoundException::new);
        return cartService.findByUserId(loggedUserId);
    }
}
