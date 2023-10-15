package com.trendyol.shoppingcartservice.card.rule;

import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CartItemMaximumCountRuleTest {

    @InjectMocks
    private CartItemMaximumCountRule cartItemMaximumCountRule;

    @Test
    void testIsValid() {
        // assert
        CartDocument object = new EasyRandom().nextObject(CartDocument.class);
        object.setItems(new EasyRandom().objects(CartItemDocument.class, 12).toList());

        // act
        boolean actual = cartItemMaximumCountRule.isValid(object);

        // assert
        assertTrue(actual);
    }

    @Test
    void testIsValid_whenRuleNotValid() {
        // assert
        CartDocument object = new EasyRandom().nextObject(CartDocument.class);
        object.setItems(new EasyRandom().objects(CartItemDocument.class, 34).toList());

        // act
        boolean actual = cartItemMaximumCountRule.isValid(object);

        // assert
        assertFalse(actual);
    }

    @Test
    void order() {
        // act
        int order = cartItemMaximumCountRule.order();

        // assert
        assertEquals(1, order);
    }
}
