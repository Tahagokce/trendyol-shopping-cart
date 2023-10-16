package com.trendyol.shoppingcartservice.card.util;

import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CartUtilTest {

    @Test
    void testCalculateAmount_whenThereIsNotPromotion(){
        // arrange
        CartItemDocument cartItemFirst = new CartItemDocument();
        cartItemFirst.setItemId(1L);
        cartItemFirst.setCategoryId(4242L);
        cartItemFirst.setSellerId(3L);
        cartItemFirst.setPrice(200.0);
        cartItemFirst.setQuantity(1);

        List<CartItemDocument> items = List.of(cartItemFirst);

        CartDocument cart = new CartDocument();
        cart.setId(1L);
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setItems(items);
        cart.setTotalAmount(4000.0);
        cart.setTotalDiscount(0.0);

        // act
        CartUtil.calculateAmount(cart);

        // assert
        Assertions.assertNull(cart.getAppliedPromotionId());
    }

    @Test
    void testCalculateAmount_whenThereIsASameSellerPromotion() {
        // arrange
        CartItemDocument cartItemFirst = new CartItemDocument();
        cartItemFirst.setItemId(1L);
        cartItemFirst.setCategoryId(4242L);
        cartItemFirst.setSellerId(3L);
        cartItemFirst.setPrice(5000.0);
        cartItemFirst.setQuantity(2);

        CartItemDocument cartItemSecond = new CartItemDocument();
        cartItemSecond.setItemId(5L);
        cartItemSecond.setCategoryId(4241L);
        cartItemSecond.setSellerId(3L);
        cartItemSecond.setPrice(5000.0);
        cartItemSecond.setQuantity(2);

        List<CartItemDocument> items = List.of(cartItemFirst, cartItemSecond);

        CartDocument cart = new CartDocument();
        cart.setId(1L);
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setItems(items);
        cart.setTotalAmount(4000.0);
        cart.setTotalDiscount(0.0);

        // act
        CartUtil.calculateAmount(cart);

        // assert
        Assertions.assertNotNull(cart.getAppliedPromotionId());
        Assertions.assertEquals(9909L, cart.getAppliedPromotionId());
    }

    @Test
    void testCalculateAmount_whenThereIsACategoryPromotion() {
        // arrange
        CartItemDocument cartItemFirst = new CartItemDocument();
        cartItemFirst.setItemId(1L);
        cartItemFirst.setCategoryId(3003L);
        cartItemFirst.setSellerId(3L);
        cartItemFirst.setPrice(200.0);
        cartItemFirst.setQuantity(1);

        CartItemDocument cartItemSecond = new CartItemDocument();
        cartItemSecond.setItemId(5L);
        cartItemSecond.setCategoryId(3003L);
        cartItemSecond.setSellerId(1L);
        cartItemSecond.setPrice(200.0);
        cartItemSecond.setQuantity(1);

        List<CartItemDocument> items = List.of(cartItemFirst, cartItemSecond);

        CartDocument cart = new CartDocument();
        cart.setId(1L);
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setItems(items);
        cart.setTotalAmount(4000.0);
        cart.setTotalDiscount(0.0);

        // act
        CartUtil.calculateAmount(cart);

        // assert
        Assertions.assertNotNull(cart.getAppliedPromotionId());
        Assertions.assertEquals(5676L, cart.getAppliedPromotionId());
    }

    @Test
    void testCalculateAmount_whenThereIsTotalPricePromotion() {
        // arrange
        CartItemDocument cartItemFirst = new CartItemDocument();
        cartItemFirst.setItemId(1L);
        cartItemFirst.setCategoryId(90L);
        cartItemFirst.setSellerId(3L);
        cartItemFirst.setPrice(250.0);
        cartItemFirst.setQuantity(1);

        CartItemDocument cartItemSecond = new CartItemDocument();
        cartItemSecond.setItemId(5L);
        cartItemSecond.setCategoryId(103L);
        cartItemSecond.setSellerId(1L);
        cartItemSecond.setPrice(251.0);
        cartItemSecond.setQuantity(1);

        List<CartItemDocument> items = List.of(cartItemFirst, cartItemSecond);

        CartDocument cart = new CartDocument();
        cart.setId(1L);
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setItems(items);
        cart.setTotalAmount(4000.0);
        cart.setTotalDiscount(0.0);

        // act
        CartUtil.calculateAmount(cart);

        // assert
        Assertions.assertNotNull(cart.getAppliedPromotionId());
        Assertions.assertEquals(1232L, cart.getAppliedPromotionId());
    }
}
