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
class CartItemUniqueMaximumCountRuleTest {

    @InjectMocks
    private CartItemUniqueMaximumCountRule cartItemUniqueMaximumCountRule;

    @Test
    void isValid() {
        // assert
        CartDocument object = new EasyRandom().nextObject(CartDocument.class);
        object.setItems(new EasyRandom().objects(CartItemDocument.class, 4).toList());

        // act
        boolean actual = cartItemUniqueMaximumCountRule.isValid(object);

        // assert
        assertTrue(actual);
    }

    @Test
    void isValid_whenRuleIsNotValid() {
        // assert
        CartDocument object = new EasyRandom().nextObject(CartDocument.class);
        object.setItems(new EasyRandom().objects(CartItemDocument.class, 34).toList());

        // act
        boolean actual = cartItemUniqueMaximumCountRule.isValid(object);

        // assert
        assertFalse(actual);
    }

    @Test
    void order() {
        // act
        int order = cartItemUniqueMaximumCountRule.order();

        // assert
        assertEquals(0, order);
    }
}
