package com.trendyol.shoppingcartservice.service.impl;

import com.trendyol.common.exception.RuleNotValidException;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.card.service.CartService;
import com.trendyol.shoppingcartservice.rule.executor.AddItemValidationRuleExecutor;
import com.trendyol.shoppingcartservice.rule.executor.AddVasItemToItemValidationRuleExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ShoppingCardServiceImplTest {

    @InjectMocks
    private ShoppingCardServiceImpl shoppingCardService;

    @Mock
    private CartService cartService;

    @Mock
    private AddItemValidationRuleExecutor addItemValidationRuleExecutor;

    @Mock
    private AddVasItemToItemValidationRuleExecutor addVasItemToItemValidationRuleExecutor;

    @Test
    void testAddItem_whenFirstItem() {
        // arrange
        CartItemDocument cartItem = new CartItemDocument();
        cartItem.setItemId(1L);
        cartItem.setCategoryId(2L);
        cartItem.setSellerId(3L);
        cartItem.setPrice(100.0);
        cartItem.setQuantity(2);

        CartDocument cart = getEmptyCart();

        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        shoppingCardService.addItem(cartItem);

        // assert
        Assertions.assertFalse(cart.getItems().isEmpty());
        Mockito.verify(cartService).findByUserId(any());
        Mockito.verify(cartService).save(cart);
        Mockito.verify(addItemValidationRuleExecutor).validator(cart);
    }

    @Test
    void testAddItem_whenIfThereIsAnotherItemIncreaseTheQuantity() {
        // arrange
        int quantity = 2;
        CartItemDocument cartItem = new CartItemDocument();
        cartItem.setItemId(1L);
        cartItem.setCategoryId(2L);
        cartItem.setSellerId(3L);
        cartItem.setPrice(100.0);
        cartItem.setQuantity(quantity);

        CartDocument cart = getEmptyCart();
        CartItemDocument currentCartItem = getEmptyCartItem();
        List<CartItemDocument> currentCartItems = List.of(currentCartItem);

        cart.setItems(currentCartItems);
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        shoppingCardService.addItem(cartItem);

        // assert
        Assertions.assertEquals(currentCartItem.getQuantity(), cartItem.getQuantity() + 1);
        Mockito.verify(cartService).findByUserId(any());
        Mockito.verify(cartService).save(cart);
        Mockito.verify(addItemValidationRuleExecutor).validator(cart);
    }

    @Test
    void testAddVasItemToItem_whenIfNoItemFoundToAdd() {
        // arrange
        VasItemDocument vasItem = new VasItemDocument();
        vasItem.setVasItemId(1L);
        vasItem.setItemId(1L);
        vasItem.setCategoryId(1L);
        vasItem.setSellerId(1L);
        vasItem.setPrice(10.0);
        vasItem.setQuantity(1);

        CartDocument cart = getEmptyCart();
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        Executable executable = () -> shoppingCardService.addVasItemToItem(vasItem);

        // assert
        Assertions.assertThrows(RuleNotValidException.class, executable);
        Mockito.verify(cartService).findByUserId(any());
    }

    @Test
    void testAddVasItemToItem_whenTheProductToBeAddedHasBeenFoundButThereIsNoVasItemYet() {
        // arrange
        VasItemDocument vasItem = new VasItemDocument();
        vasItem.setVasItemId(1L);
        vasItem.setItemId(1L);
        vasItem.setCategoryId(1L);
        vasItem.setSellerId(1L);
        vasItem.setPrice(10.0);
        vasItem.setQuantity(1);

        CartDocument cart = getEmptyCart();

        CartItemDocument currentCartItem = getEmptyCartItem();
        currentCartItem.setQuantity(1);
        currentCartItem.setItemId(1L);
        currentCartItem.setCategoryId(3004L);

        cart.setItems(List.of(currentCartItem));
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        shoppingCardService.addVasItemToItem(vasItem);

        // assert
        Assertions.assertFalse(currentCartItem.getVasItems().isEmpty());
        Mockito.verify(cartService).findByUserId(any());
        Mockito.verify(addVasItemToItemValidationRuleExecutor).validator(any(), any());
    }

    @Test
    void testAddVasItemToItem_whenAddSomeAmountToExistingVasItem() {
        // arrange
        int quantity = 1;
        VasItemDocument vasItem = new VasItemDocument();
        vasItem.setVasItemId(1L);
        vasItem.setItemId(1L);
        vasItem.setCategoryId(1L);
        vasItem.setSellerId(1L);
        vasItem.setPrice(10.0);
        vasItem.setQuantity(quantity);

        CartDocument cart = getEmptyCart();

        CartItemDocument currentCartItem = getEmptyCartItem();
        currentCartItem.setQuantity(1);
        currentCartItem.setItemId(1L);
        currentCartItem.setCategoryId(3004L);

        VasItemDocument currentVasItem = new VasItemDocument();
        currentVasItem.setVasItemId(1L);
        currentVasItem.setItemId(1L);
        currentVasItem.setCategoryId(1L);
        currentVasItem.setSellerId(1L);
        currentVasItem.setPrice(10.0);
        currentVasItem.setQuantity(3);

        currentCartItem.setVasItems(List.of(currentVasItem));

        cart.setItems(List.of(currentCartItem));
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        shoppingCardService.addVasItemToItem(vasItem);

        // assert
        Assertions.assertEquals(currentVasItem.getQuantity(), vasItem.getQuantity());
        Mockito.verify(cartService).findByUserId(any());
        Mockito.verify(addVasItemToItemValidationRuleExecutor).validator(any(), any());
    }

    @Test
    void testAddVasItemToItem_whenIfaDifferentVasItemIsAdded() {
        // arrange
        int quantity = 1;
        VasItemDocument vasItem = new VasItemDocument();
        vasItem.setVasItemId(1L);
        vasItem.setItemId(1L);
        vasItem.setCategoryId(1L);
        vasItem.setSellerId(1L);
        vasItem.setPrice(10.0);
        vasItem.setQuantity(quantity);

        CartDocument cart = getEmptyCart();

        CartItemDocument currentCartItem = getEmptyCartItem();
        currentCartItem.setQuantity(1);
        currentCartItem.setItemId(1L);
        currentCartItem.setCategoryId(1001L);

        VasItemDocument currentVasItem = new VasItemDocument();
        currentVasItem.setVasItemId(1L);
        currentVasItem.setItemId(1L);
        currentVasItem.setCategoryId(1L);
        currentVasItem.setSellerId(1L);
        currentVasItem.setPrice(10.0);
        currentVasItem.setQuantity(3);

        currentCartItem.setVasItems(List.of(currentVasItem));

        cart.setItems(List.of(currentCartItem));
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        shoppingCardService.addVasItemToItem(vasItem);

        // assert
        Assertions.assertEquals(currentVasItem.getQuantity(), vasItem.getQuantity());
        Mockito.verify(cartService).findByUserId(any());
        Mockito.verify(addVasItemToItemValidationRuleExecutor).validator(any(), any());
    }

    @Test
    void testRemoveItem_whenIfThereIsNoRelevantItemInTheCart() {
        // arrange
        Long itemId = 1L ;
        CartDocument cart = getEmptyCart();
        CartItemDocument emptyCartItem = getEmptyCartItem();
        emptyCartItem.setItemId(itemId);

        cart.setItems(List.of(emptyCartItem));
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        Executable executable = () -> shoppingCardService.removeItem(2L);
        
        // assert
        Assertions.assertThrows(RuleNotValidException.class, executable);
        Mockito.verify(cartService).findByUserId(any());

    }

    @Test
    void testRemoveItem() {
        // arrange
        Long itemId = 1L ;
        CartDocument cart = getEmptyCart();
        CartItemDocument emptyCartItem = getEmptyCartItem();
        emptyCartItem.setItemId(itemId);

        cart.setItems(List.of(emptyCartItem));
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        shoppingCardService.removeItem(itemId);

        // assert
        Assertions.assertTrue(cart.getItems().isEmpty());
        Mockito.verify(cartService).findByUserId(any());

    }

    @Test
    void testResetCart() {
        // arrange
        CartDocument cart = getEmptyCart();
        cart.setTotalAmount(100.00);
        cart.setUserId(1L);
        CartItemDocument emptyCartItem = getEmptyCartItem();
        emptyCartItem.setItemId(1L);

        cart.setItems(List.of(emptyCartItem));
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        shoppingCardService.resetCart();

        // assert
        Assertions.assertEquals(1L, cart.getUserId());
        Mockito.verify(cartService).findByUserId(any());
    }

    @Test
    void displayCart() {
        // arrange
        CartDocument cart = getEmptyCart();
        cart.setTotalAmount(100.00);
        cart.setUserId(1L);
        CartItemDocument emptyCartItem = getEmptyCartItem();
        emptyCartItem.setItemId(1L);

        cart.setItems(List.of(emptyCartItem));
        Mockito.when(cartService.findByUserId(any())).thenReturn(cart);

        // act
        CartDocument actual = shoppingCardService.displayCart();

        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(cart.getUserId(), actual.getUserId());
        Mockito.verify(cartService).findByUserId(any());
    }

    private CartDocument getEmptyCart(){
        CartDocument cart = new CartDocument();
        cart.setCartId(9292L);
        cart.setTotalAmount(0.0);
        cart.setTotalDiscount(0.0);
        cart.setAppliedPromotionId(null);
        return cart;
    }

    private CartItemDocument getEmptyCartItem(){
        CartItemDocument cartItem = new CartItemDocument();
        cartItem.setItemId(1L);
        cartItem.setCategoryId(2L);
        cartItem.setSellerId(3L);
        cartItem.setPrice(100.0);
        cartItem.setQuantity(1);
        return cartItem;
    }
}
