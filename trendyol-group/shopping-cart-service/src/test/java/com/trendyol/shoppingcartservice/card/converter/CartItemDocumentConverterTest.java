package com.trendyol.shoppingcartservice.card.converter;

import com.trendyol.common.model.request.shoppingcart.AddItemRequest;
import com.trendyol.common.model.resource.CartItemResource;
import com.trendyol.entity.db.cart.CartItem;
import com.trendyol.entity.document.cart.CartItemDocument;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CartItemDocumentConverterTest {

    @InjectMocks
    private CartItemDocumentConverter cartItemDocumentConverter;

    @Mock
    private VasItemConverter vasItemConverter;

    @Test
    void toDocument() {
        // arrange
        AddItemRequest request = new EasyRandom().nextObject(AddItemRequest.class);

        // act
        CartItemDocument actual = cartItemDocumentConverter.toDocument(request);

        // assert
        assertNotNull(actual);
    }

    @Test
    void testToDocument() {
        // arrange
        CartItem entity = new EasyRandom().nextObject(CartItem.class);

        // act
        CartItemDocument actual = cartItemDocumentConverter.toDocument(entity);

        // assert
        assertNotNull(actual);
    }

    @Test
    void testToResource() {
        // arrange
        CartItemDocument document = new EasyRandom().nextObject(CartItemDocument.class);

        // act
        CartItemResource actual = cartItemDocumentConverter.toResource(document);

        // assert
        assertNotNull(actual);
    }
}
