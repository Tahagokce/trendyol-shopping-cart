package com.trendyol.shoppingcartservice.card.converter;

import com.trendyol.common.model.resource.CartResource;
import com.trendyol.entity.db.cart.Cart;
import com.trendyol.entity.document.cart.CartDocument;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CartDocumentConverterTest {

    @InjectMocks
    private CartDocumentConverter cartDocumentConverter;

    @Mock
    private CartItemDocumentConverter cartItemDocumentConverter;

    @Test
    void testToCartDocument() {
        // arrange
        Cart entity = new EasyRandom().nextObject(Cart.class);

        // act
        CartDocument actual = cartDocumentConverter.toCartDocument(entity);

        // assert
        assertNotNull(actual);
    }

    @Test
    void testToResource() {
        // arrange
        CartDocument document = new EasyRandom().nextObject(CartDocument.class);

        // act
        CartResource actual = cartDocumentConverter.toResource(document);

        // assert
        assertNotNull(actual);
    }
}
