package com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddVasItemToItemMaxQuantityRuleTest {

    @InjectMocks
    private AddVasItemToItemMaxQuantityRule addVasItemToItemMaxQuantityRule;

    @Test
    void testIsValid_whenIfVasItemIsMoreThanItem() {
        // arrange
        VasItemDocument vasItems = new VasItemDocument();
        vasItems.setQuantity(7);

        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);
        cartItem.setQuantity(2);

        // act
        ValidationMessageResource actual = addVasItemToItemMaxQuantityRule.isValid(vasItems, cartItem);

        // assert
        Assertions.assertFalse(actual.isValid());
    }

    @Test
    void testIsValid_whenIfVasItemIsLessThanItem() {
        // arrange
        VasItemDocument vasItems = new VasItemDocument();
        vasItems.setQuantity(6);

        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);
        cartItem.setQuantity(2);

        // act
        ValidationMessageResource actual = addVasItemToItemMaxQuantityRule.isValid(vasItems, cartItem);

        // assert
        Assertions.assertTrue(actual.isValid());
    }


    @Test
    void testOrder() {
        // act
        int actual = addVasItemToItemMaxQuantityRule.order();

        // assert
        Assertions.assertEquals(3, actual);
    }
}
