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
class AddVasItemToItemPriceRuleTest {

    @InjectMocks
    private AddVasItemToItemPriceRule addVasItemToItemPriceRule;

    @Test
    void testIsValid_whenCartItemCartItemPriceIsMoreThanVasItem() {
        // arrange
        VasItemDocument vasItems = new VasItemDocument();
        vasItems.setPrice(70.0);

        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);
        cartItem.setPrice(100.0);

        // act
        ValidationMessageResource actual = addVasItemToItemPriceRule.isValid(vasItems, cartItem);

        // assert
        Assertions.assertTrue(actual.isValid());
    }

    @Test
    void testIsValid_whenCartItemCartItemPriceIsLESSThanVasItem() {
        // arrange
        VasItemDocument vasItems = new VasItemDocument();
        vasItems.setPrice(170.0);

        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);
        cartItem.setPrice(100.0);

        // act
        ValidationMessageResource actual = addVasItemToItemPriceRule.isValid(vasItems, cartItem);

        // assert
        Assertions.assertFalse(actual.isValid());
    }

    @Test
    void testOrder() {
        // act
        int actual = addVasItemToItemPriceRule.order();

        // assert
        Assertions.assertEquals(2, actual);
    }
}
