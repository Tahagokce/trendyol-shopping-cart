package com.trendyol.common.model.input.cart;

import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AddVasItemToItemInputPayloadTest {

    private AddVasItemToItemInputPayload expected;

    @BeforeEach
    void setUp() {
        expected = new EasyRandom().nextObject(AddVasItemToItemInputPayload.class);
    }

    @Test
    void testGetterSetter() {
        // arrange
        AddVasItemToItemInputPayload actual = new AddVasItemToItemInputPayload();
        actual.setItemId(expected.getItemId());
        actual.setVasItemId(expected.getVasItemId());
        actual.setVasCategoryId(expected.getVasCategoryId());
        actual.setVasSellerId(expected.getVasSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());

        // act
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testToString() {
        // arrange
        AddVasItemToItemInputPayload actual = new AddVasItemToItemInputPayload();
        actual.setItemId(expected.getItemId());
        actual.setVasItemId(expected.getVasItemId());
        actual.setVasCategoryId(expected.getVasCategoryId());
        actual.setVasSellerId(expected.getVasSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());

        // act
        assertEquals(actual.toString(), expected.toString());
    }
}
