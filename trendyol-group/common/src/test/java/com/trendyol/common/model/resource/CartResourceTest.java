package com.trendyol.common.model.resource;

import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CartResourceTest {

    private CartResource expected;

    @BeforeEach
    void setUp() {
        expected = new EasyRandom().nextObject(CartResource.class);
    }

    @Test
    void testGetterSetter() {
        // act
        CartResource actual = new CartResource();
        actual.setTotalAmount(expected.getTotalAmount());
        actual.setAppliedPromotionId(expected.getAppliedPromotionId());
        actual.setTotalDiscount(expected.getTotalDiscount());
        actual.setItems(expected.getItems());

        // assert
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testToString() {
        // act
        CartResource actual = new CartResource();
        actual.setTotalAmount(expected.getTotalAmount());
        actual.setAppliedPromotionId(expected.getAppliedPromotionId());
        actual.setTotalDiscount(expected.getTotalDiscount());
        actual.setItems(expected.getItems());

        // assert
        assertEquals(actual.toString(), expected.toString());
    }
}
