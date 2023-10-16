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
class AddVasItemToItemSellerRuleTest {
    @InjectMocks
    private AddVasItemToItemSellerRule addVasItemToItemSellerRule;

    @Test
    void testIsValid_whenIsVasItemSeller() {
        // arrange
        VasItemDocument vasItems = new VasItemDocument();
        vasItems.setSellerId(5003L);

        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);
        cartItem.setPrice(100.0);

        // act
        ValidationMessageResource actual = addVasItemToItemSellerRule.isValid(vasItems, cartItem);

        // assert
        Assertions.assertTrue(actual.isValid());
    }

    @Test
    void testIsValid_whenIsNotVasItemSeller() {
        // arrange
        VasItemDocument vasItems = new VasItemDocument();
        vasItems.setSellerId(10203L);

        CartItemDocument cartItem = new EasyRandom().nextObject(CartItemDocument.class);

        // act
        ValidationMessageResource actual = addVasItemToItemSellerRule.isValid(vasItems, cartItem);

        // assert
        Assertions.assertFalse(actual.isValid());
    }

    @Test
    void testOrder() {
        // act
        int actual = addVasItemToItemSellerRule.order();

        // assert
        Assertions.assertEquals(0, actual);
    }
}
