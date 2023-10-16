package com.trendyol.shoppingcartservice.rule.additem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddItemMaximumAmountRuleTest {

    @InjectMocks
    private AddItemMaximumAmountRule addItemMaximumAmountRule;

    @Test
    void testIsValid_whenCartTotalAmountIfGreaterThanFiveHundredThousand() {
        // arrange
        CartDocument cart = new EasyRandom().nextObject(CartDocument.class);
        cart.setTotalAmount(600000.0);

        // act
        ValidationMessageResource actual = addItemMaximumAmountRule.isValid(cart);

        // assert
        Assertions.assertFalse(actual.isValid());
    }

    @Test
    void testIsValid_whenCartTotalAmountIfLessThanFiveHundredThousand() {
        // arrange
        CartDocument cart = new EasyRandom().nextObject(CartDocument.class);
        cart.setTotalAmount(100000.0);

        // act
        ValidationMessageResource actual = addItemMaximumAmountRule.isValid(cart);

        // assert
        Assertions.assertTrue(actual.isValid());
    }

    @Test
    void testOrder() {
        // act
        int actual = addItemMaximumAmountRule.order();

        // assert
        Assertions.assertEquals(2, actual);
    }
}
