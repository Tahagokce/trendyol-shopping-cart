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
class AddItemUniqueMaximumCountRuleTest {

    @InjectMocks
    private AddItemUniqueMaximumCountRule addItemUniqueMaximumCountRule;

    @Test
    void testIsValid_whenNotUniqueItemCountEleven() {
        // arrange
        CartDocument cart = new CartDocument();
        List<CartItemDocument> cartItems = new EasyRandom().objects(CartItemDocument.class, 11).toList();

        Long id = 1L;
        for (CartItemDocument cartItem: cartItems){
            cartItem.setItemId(id);
            id++;
        }

        cart.setItems(cartItems);

        // act
        ValidationMessageResource actual = addItemUniqueMaximumCountRule.isValid(cart);

        // assert
        Assertions.assertFalse(actual.isValid());
    }

    @Test
    void testIsValid_whenNotUniqueItemCountNine() {
        // arrange
        CartDocument cart = new CartDocument();
        List<CartItemDocument> cartItems = new EasyRandom().objects(CartItemDocument.class, 9).toList();

        Long id = 1L;
        for (CartItemDocument cartItem: cartItems){
            cartItem.setItemId(id);
            id++;
        }

        cart.setItems(cartItems);

        // act
        ValidationMessageResource actual = addItemUniqueMaximumCountRule.isValid(cart);

        // assert
        Assertions.assertTrue(actual.isValid());
    }

    @Test
    void testOrder() {
        // act
        int actual = addItemUniqueMaximumCountRule.order();

        // assert
        Assertions.assertEquals(0, actual);
    }
}
