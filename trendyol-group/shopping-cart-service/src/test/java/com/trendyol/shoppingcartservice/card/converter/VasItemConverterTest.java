package com.trendyol.shoppingcartservice.card.converter;

import com.trendyol.common.model.request.shoppingcart.AddVasItemToItemRequest;
import com.trendyol.common.model.resource.VasItemResource;
import com.trendyol.entity.document.cart.VasItemDocument;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class VasItemConverterTest {

    @InjectMocks
    private VasItemConverter vasItemConverter;

    @Test
    void testToDocument() {
        // arrange
        AddVasItemToItemRequest request = new EasyRandom().nextObject(AddVasItemToItemRequest.class);

        // act
        VasItemDocument actual = vasItemConverter.toDocument(request);

        // assert
        assertNotNull(actual);
    }

    @Test
    void testToResource() {
        // arrange
        VasItemDocument document = new EasyRandom().nextObject(VasItemDocument.class);

        // act
        VasItemResource actual = vasItemConverter.toResource(document);

        // assert
        assertNotNull(actual);
    }
}
