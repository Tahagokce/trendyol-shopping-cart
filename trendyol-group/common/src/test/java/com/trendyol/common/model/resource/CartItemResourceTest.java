package com.trendyol.common.model.resource;

import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CartItemResourceTest {

    private CartItemResource expected;

    @BeforeEach
    void setUp(){
        expected = new EasyRandom().nextObject(CartItemResource.class);
    }


    @Test
    void testGetterSetter() {
        // arrange
        CartItemResource actual = new CartItemResource();
        actual.setItemId(expected.getItemId());
        actual.setCategoryId(expected.getCategoryId());
        actual.setSellerId(expected.getSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());
        actual.setVasItems(expected.getVasItems());

        // act
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testToString() {
        // arrange
        CartItemResource actual = new CartItemResource();
        actual.setItemId(expected.getItemId());
        actual.setCategoryId(expected.getCategoryId());
        actual.setSellerId(expected.getSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());
        actual.setVasItems(expected.getVasItems());

        // act
        assertEquals(actual.toString(), expected.toString());
    }
}
