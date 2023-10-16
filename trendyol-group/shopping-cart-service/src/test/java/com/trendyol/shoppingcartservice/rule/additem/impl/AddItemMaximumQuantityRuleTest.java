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
class AddItemMaximumQuantityRuleTest {

    @InjectMocks
    private AddItemMaximumQuantityRule addItemMaximumQuantityRule;

    @Test
    void testIsValid_whenItemIsDigitalItem() {
        // arrange
        CartDocument cart = new CartDocument();

        List<CartItemDocument> cartItems = new EasyRandom().objects(CartItemDocument.class, 3).toList();
        cartItems.forEach(item -> {
            item.setQuantity(10);
            item.setCategoryId(7889L);
        });

        cart.setItems(cartItems);

        // act
        ValidationMessageResource actual = addItemMaximumQuantityRule.isValid(cart);

        // assert
        Assertions.assertFalse(actual.isValid());
    }

    @Test
    void testIsValid_whenItemIsDefaultItem() {
        // arrange
        CartDocument cart = new CartDocument();

        List<CartItemDocument> cartItems = new EasyRandom().objects(CartItemDocument.class, 3).toList();
        cartItems.forEach(item -> {
            item.setQuantity(9);
            item.setCategoryId(700L);
        });

        cart.setItems(cartItems);

        // act
        ValidationMessageResource actual = addItemMaximumQuantityRule.isValid(cart);

        // assert
        Assertions.assertTrue(actual.isValid());
    }

    @Test
    void testOrder() {
        // act
        int actual = addItemMaximumQuantityRule.order();

        // assert
        Assertions.assertEquals(3, actual);
    }
}
