package com.trendyol.entity.db.promotion;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PromotionTest {
    private Promotion expected;

    @BeforeEach
    void setUp() {
        expected = new EasyRandom().nextObject(Promotion.class);
    }

    @Test
    void testEquals() {
        // act
        Promotion actual = new Promotion();
        actual.setType(expected.getType());
        actual.setRate(expected.getRate());
        actual.setId(expected.getId());
        actual.setCreatedDate(expected.getCreatedDate());
        actual.setLastModifiedDate(expected.getLastModifiedDate());
        actual.setCreatedBy(expected.getCreatedBy());
        actual.setLastModifiedBy(expected.getLastModifiedBy());
        actual.setEnable(expected.isEnable());
        actual.setTransactionId(expected.getTransactionId());
        actual.setDeleted(expected.isDeleted());

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void testHashCode() {
        // act
        Promotion actual = new Promotion();
        actual.setType(expected.getType());
        actual.setRate(expected.getRate());
        actual.setId(expected.getId());
        actual.setCreatedDate(expected.getCreatedDate());
        actual.setLastModifiedDate(expected.getLastModifiedDate());
        actual.setCreatedBy(expected.getCreatedBy());
        actual.setLastModifiedBy(expected.getLastModifiedBy());
        actual.setEnable(expected.isEnable());
        actual.setTransactionId(expected.getTransactionId());
        actual.setDeleted(expected.isDeleted());

        // assert
        Assertions.assertEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    void testGetterSetter() {
        // act
        Promotion actual = new Promotion();
        actual.setType(expected.getType());
        actual.setRate(expected.getRate());
        actual.setId(expected.getId());
        actual.setCreatedDate(expected.getCreatedDate());
        actual.setLastModifiedDate(expected.getLastModifiedDate());
        actual.setCreatedBy(expected.getCreatedBy());
        actual.setLastModifiedBy(expected.getLastModifiedBy());
        actual.setEnable(expected.isEnable());
        actual.setDeleted(expected.isDeleted());
        actual.setTransactionId(expected.getTransactionId());

        // assert
        org.assertj.core.api.Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
