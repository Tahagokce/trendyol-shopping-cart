package com.trendyol.common.model.request.shoppingcart;

import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AddItemRequestTest {

    private AddItemRequest expected;

    @BeforeEach
    void setUp() {
        expected = new EasyRandom().nextObject(AddItemRequest.class);
    }

    @Test
    void testGetterSetter() {
        // arrange
        AddItemRequest actual = new AddItemRequest();
        actual.setItemId(expected.getItemId());
        actual.setCategoryId(expected.getCategoryId());
        actual.setSellerId(expected.getSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());

        // act
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testToString() {
        // arrange
        AddItemRequest actual = new AddItemRequest();
        actual.setItemId(expected.getItemId());
        actual.setCategoryId(expected.getCategoryId());
        actual.setSellerId(expected.getSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());

        // act
        assertEquals(actual.toString(), expected.toString());
    }
}
