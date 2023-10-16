package com.trendyol.entity.document.cart;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VasItemDocumentTest {
    private VasItemDocument expected;

    @BeforeEach
    void setUp() {
        expected = new EasyRandom().nextObject(VasItemDocument.class);
    }

    @Test
    void testEquals() {
        // act
        VasItemDocument actual = new VasItemDocument();
        actual.setVasItemId(expected.getVasItemId());
        actual.setItemId(expected.getItemId());
        actual.setCategoryId(expected.getCategoryId());
        actual.setSellerId(expected.getSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void testHashCode() {
        // act
        VasItemDocument actual = new VasItemDocument();
        actual.setVasItemId(expected.getVasItemId());
        actual.setItemId(expected.getItemId());
        actual.setCategoryId(expected.getCategoryId());
        actual.setSellerId(expected.getSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());

        // assert
        Assertions.assertEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    void testGetterSetter() {
        // act
        VasItemDocument actual = new VasItemDocument();
        actual.setVasItemId(expected.getVasItemId());
        actual.setItemId(expected.getItemId());
        actual.setCategoryId(expected.getCategoryId());
        actual.setSellerId(expected.getSellerId());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());

        // assert
        org.assertj.core.api.Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
