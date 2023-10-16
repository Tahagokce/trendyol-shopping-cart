package com.trendyol.entity.document.cart;

import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartDocumentTest {

    private CartDocument expected;

    @BeforeEach
    void setUp() {
        expected = new EasyRandom().nextObject(CartDocument.class);
    }

    @Test
    void testGetterSetter() {
        // act
        CartDocument actual = new CartDocument();
        actual.setId(expected.getId());
        actual.setCartId(expected.getCartId());
        actual.setUserId(expected.getUserId());
        actual.setItems(expected.getItems());
        actual.setTotalAmount(expected.getTotalAmount());
        actual.setTotalDiscount(expected.getTotalDiscount());
        actual.setAppliedPromotionId(expected.getAppliedPromotionId());

        // arrange
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
