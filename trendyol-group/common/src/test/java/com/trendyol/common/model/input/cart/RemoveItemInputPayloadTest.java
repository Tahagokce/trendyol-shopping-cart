package com.trendyol.common.model.input.cart;

import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RemoveItemInputPayloadTest {

    private RemoveItemInputPayload expected;

    @BeforeEach
    void setUp() {
        expected = new EasyRandom().nextObject(RemoveItemInputPayload.class);
    }

    @Test
    void testGetterSetter() {
        // arrange
        RemoveItemInputPayload actual = new RemoveItemInputPayload();
        actual.setItemId(expected.getItemId());

        // act
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testToString() {
        // arrange
        RemoveItemInputPayload actual = new RemoveItemInputPayload();
        actual.setItemId(expected.getItemId());

        // act
        assertEquals(actual.toString(), expected.toString());
    }
}
