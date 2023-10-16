package com.trendyol.entity.db.cart;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CartItemTest {
    private CartItem expected;

    @BeforeEach
    void setUp() {
        expected = new EasyRandom().nextObject(CartItem.class);
    }

    @Test
    void testEquals() {
        // act
        CartItem actual = new CartItem();
        actual.setCart(expected.getCart());
        actual.setItem(expected.getItem());
        actual.setSeller(expected.getSeller());
        actual.setCategory(expected.getCategory());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());
        actual.setId(expected.getId());
        actual.setCreatedDate(expected.getCreatedDate());
        actual.setLastModifiedDate(expected.getLastModifiedDate());
        actual.setCreatedBy(expected.getCreatedBy());
        actual.setLastModifiedBy(expected.getLastModifiedBy());
        actual.setEnable(expected.isEnable());
        actual.setDeleted(expected.isDeleted());
        actual.setTransactionId(expected.getTransactionId());

        // assert
        assertEquals(expected, actual);

    }

    @Test
    void testHashCode() {
        // act
        CartItem actual = new CartItem();
        actual.setCart(expected.getCart());
        actual.setItem(expected.getItem());
        actual.setSeller(expected.getSeller());
        actual.setCategory(expected.getCategory());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());
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
        CartItem actual = new CartItem();
        actual.setCart(expected.getCart());
        actual.setItem(expected.getItem());
        actual.setSeller(expected.getSeller());
        actual.setCategory(expected.getCategory());
        actual.setPrice(expected.getPrice());
        actual.setQuantity(expected.getQuantity());
        actual.setId(expected.getId());
        actual.setCreatedDate(expected.getCreatedDate());
        actual.setLastModifiedDate(expected.getLastModifiedDate());
        actual.setCreatedBy(expected.getCreatedBy());
        actual.setLastModifiedBy(expected.getLastModifiedBy());
        actual.setEnable(expected.isEnable());
        actual.setTransactionId(expected.getTransactionId());
        actual.setDeleted(expected.isDeleted());

        // assert
        org.assertj.core.api.Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
