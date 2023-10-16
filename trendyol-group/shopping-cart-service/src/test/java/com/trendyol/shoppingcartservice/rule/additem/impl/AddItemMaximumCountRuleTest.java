package com.trendyol.shoppingcartservice.rule.additem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AddItemMaximumCountRuleTest {

    @InjectMocks
    private AddItemMaximumCountRule addItemMaximumCountRule;

    @Test
    void testIsValid_whenCartItemQuantityGreaterThenThirty() {
        // arrange
        CartDocument cart = new CartDocument();
        List<CartItemDocument> cartItems = new EasyRandom().objects(CartItemDocument.class, 3).toList();
        cartItems.forEach(item -> item.setQuantity(100));
        cart.setItems(cartItems);

        // act
        ValidationMessageResource actual = addItemMaximumCountRule.isValid(cart);

        // assert
        Assertions.assertFalse(actual.isValid());
    }

    @Test
    void testIsValid_whenCartItemQuantityLessThenThirty() {
        // arrange
        CartDocument cart = new CartDocument();
        List<CartItemDocument> cartItems = new EasyRandom().objects(CartItemDocument.class, 3).toList();
        cartItems.forEach(item -> item.setQuantity(1));
        cart.setItems(cartItems);

        // act
        ValidationMessageResource actual = addItemMaximumCountRule.isValid(cart);

        // assert
        Assertions.assertTrue(actual.isValid());
    }

    @Test
    void testOrder() {
        // act
        int actual = addItemMaximumCountRule.order();

        // assert
        Assertions.assertEquals(1, actual);
    }
}
