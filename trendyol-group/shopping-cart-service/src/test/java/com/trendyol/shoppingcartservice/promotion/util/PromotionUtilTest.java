package com.trendyol.shoppingcartservice.promotion.util;

import com.trendyol.core.model.dto.AppliedPromotionDto;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.shoppingcartservice.card.util.CartUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PromotionUtilTest {

    @Test
    void testApplyMostAdvantagePromotion_whenThereIsNotPromotion() {
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
        org.junit.jupiter.api.Assertions.assertNull(cart.getAppliedPromotionId());
    }

    @Test
    void testApplyMostAdvantagePromotion_whenThereIsASameSellerPromotion() {
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

        AppliedPromotionDto expected = PromotionUtil.calculateSameSellerPromotion(cart);

        // act
        AppliedPromotionDto actual = PromotionUtil.applyMostAdvantagePromotion(cart);

        // assert
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testApplyMostAdvantagePromotion_whenThereIsACategoryPromotion() {
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

        AppliedPromotionDto expected = PromotionUtil.calculateCategoryPromotion(cart);

        // act
        AppliedPromotionDto actual = PromotionUtil.applyMostAdvantagePromotion(cart);

        // assert
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testApplyMostAdvantagePromotion_whenThereIsATotalPricePromotion() {
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

        AppliedPromotionDto expected = PromotionUtil.calculateTotalPricePromotion(cart);

        // act
        AppliedPromotionDto actual = PromotionUtil.applyMostAdvantagePromotion(cart);

        // assert
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testCalculateSameSellerPromotion_whenItIsPossible() {
        // arrange
        CartItemDocument cartItemFirst = new CartItemDocument();
        cartItemFirst.setItemId(1L);
        cartItemFirst.setCategoryId(4242L);
        cartItemFirst.setSellerId(3L);
        cartItemFirst.setPrice(200.0);
        cartItemFirst.setQuantity(2);

        List<CartItemDocument> items = List.of(cartItemFirst);

        CartDocument cart = new CartDocument();
        cart.setId(1L);
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setItems(items);
        cart.setTotalAmount(4000.0);
        cart.setTotalDiscount(0.0);

        // act
        AppliedPromotionDto actual = PromotionUtil.calculateSameSellerPromotion(cart);

        // assert
        org.junit.jupiter.api.Assertions.assertNotEquals(cart.getTotalAmount(), actual.getTotalAmount());
    }

    @Test
    void testCalculateSameSellerPromotion_whenItIsNotPossible() {
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
        AppliedPromotionDto actual = PromotionUtil.calculateSameSellerPromotion(cart);

        // assert
        org.junit.jupiter.api.Assertions.assertNotEquals(cart.getTotalAmount(), actual.getTotalAmount());
    }

    @Test
    void testCalculateCategoryPromotion_whenItIsPossible() {
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
        AppliedPromotionDto actual = PromotionUtil.calculateCategoryPromotion(cart);

        // assert
        org.junit.jupiter.api.Assertions.assertNotEquals(cart.getTotalAmount(), actual.getTotalAmount());
    }

    @Test
    void testCalculateCategoryPromotion_whenItIsNotPossible() {
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
        cart.setTotalAmount(200.00);
        cart.setTotalDiscount(0.0);

        // act
        AppliedPromotionDto actual = PromotionUtil.calculateCategoryPromotion(cart);

        // assert
        org.junit.jupiter.api.Assertions.assertEquals(cart.getTotalAmount(), actual.getTotalAmount());
    }

    @Test
    void testCalculateTotalPricePromotion_whenItIsPossible() {
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
        cartItemSecond.setPrice(249.0);
        cartItemSecond.setQuantity(1);

        List<CartItemDocument> items = List.of(cartItemFirst, cartItemSecond);

        CartDocument cart = new CartDocument();
        cart.setId(1L);
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setItems(items);
        cart.setTotalAmount(499.0);
        cart.setTotalDiscount(0.0);

        // act
        AppliedPromotionDto actual = PromotionUtil.calculateTotalPricePromotion(cart);

        // assert
        org.junit.jupiter.api.Assertions.assertEquals(cart.getTotalAmount(), actual.getTotalAmount());
    }

    @ParameterizedTest
    @ValueSource(doubles = {5000.0, 10000.0, 50000.0})
    void testCalculateTotalPricePromotion_whenItIsPossibleAllCondition(Double firstItemPrice) {
        // arrange
        CartDocument cart = getPrepareCartAmount(firstItemPrice);

        // act
        AppliedPromotionDto actual = PromotionUtil.calculateTotalPricePromotion(cart);

        // assert
        org.junit.jupiter.api.Assertions.assertNotEquals(cart.getTotalAmount(), actual.getTotalAmount());
    }

    @Test
    void testCalculateTotalPricePromotion_whenItIsNotPossible() {
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
        cartItemSecond.setPrice(249.0);
        cartItemSecond.setQuantity(1);

        List<CartItemDocument> items = List.of(cartItemFirst, cartItemSecond);

        CartDocument cart = new CartDocument();
        cart.setId(1L);
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setItems(items);
        cart.setTotalAmount(499.0);
        cart.setTotalDiscount(0.0);

        // act
        AppliedPromotionDto actual = PromotionUtil.calculateTotalPricePromotion(cart);

        // assert
        org.junit.jupiter.api.Assertions.assertEquals(cart.getTotalAmount(), actual.getTotalAmount());
    }

    private CartDocument getPrepareCartAmount(Double firstItemPrice) {
        CartItemDocument cartItemFirst = new CartItemDocument();
        cartItemFirst.setItemId(1L);
        cartItemFirst.setCategoryId(90L);
        cartItemFirst.setSellerId(3L);
        cartItemFirst.setPrice(firstItemPrice);
        cartItemFirst.setQuantity(1);

        CartItemDocument cartItemSecond = new CartItemDocument();
        cartItemSecond.setItemId(5L);
        cartItemSecond.setCategoryId(103L);
        cartItemSecond.setSellerId(1L);
        cartItemSecond.setPrice(100.0);
        cartItemSecond.setQuantity(1);

        List<CartItemDocument> items = List.of(cartItemFirst, cartItemSecond);

        CartDocument cart = new CartDocument();
        cart.setId(1L);
        cart.setCartId(1L);
        cart.setUserId(1L);
        cart.setItems(items);
        cart.setTotalAmount(cartItemFirst.getPrice() + cartItemSecond.getPrice());
        cart.setTotalDiscount(0.0);
        return cart;
    }
}
