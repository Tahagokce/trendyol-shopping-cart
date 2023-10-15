package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.model.input.cart.AddVasItemToItemInputPayload;
import com.trendyol.common.model.resource.CartResource;
import com.trendyol.core.model.output.BaseFileOutput;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class DisplayCartCommandExecutorTest {

    @InjectMocks
    private DisplayCartCommandExecutor displayCartCommandExecutor;

    @Mock
    private ShoppingCartServiceClient shoppingCartServiceClient;


    @Test
    void testExecute() {
        // arrange
        CartResource resource = new EasyRandom().nextObject(CartResource.class);
        ResponseEntity<CartResource> response = ResponseEntity.ok(resource);
        Mockito.when(shoppingCartServiceClient.displayCart()).thenReturn(response);

        // act
        BaseFileOutput execute = displayCartCommandExecutor.execute(null);

        // assert
        Assertions.assertTrue(execute.isResult());
        Mockito.verify(shoppingCartServiceClient).displayCart();
    }

    @Test
    void testExecute_whenRequestIsError() {
        // arrange
        AddVasItemToItemInputPayload payload = new EasyRandom().nextObject(AddVasItemToItemInputPayload.class);
        ResponseEntity<CartResource> response = ResponseEntity.badRequest().build();
        Mockito.when(shoppingCartServiceClient.displayCart()).thenReturn(response);

        // act
        BaseFileOutput execute = displayCartCommandExecutor.execute(payload);

        // assert
        Assertions.assertFalse(execute.isResult());
        Mockito.verify(shoppingCartServiceClient).displayCart();
    }

    @Test
    void testGetCommandType() {
        // act
        ShoppingCartCommandType actual = displayCartCommandExecutor.getCommandType();

        // assert
        Assertions.assertEquals(ShoppingCartCommandType.DISPLAY_CART, actual);
    }
}
