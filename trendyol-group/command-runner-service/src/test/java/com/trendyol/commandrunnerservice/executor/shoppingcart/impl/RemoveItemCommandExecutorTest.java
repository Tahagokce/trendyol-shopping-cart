package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.model.input.cart.RemoveItemInputPayload;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RemoveItemCommandExecutorTest {

    @InjectMocks
    private RemoveItemCommandExecutor removeItemCommandExecutor;

    @Mock
    private ShoppingCartServiceClient shoppingCartServiceClient;

    @Test
    void testExecute() {
        // arrange
        RemoveItemInputPayload payload = new EasyRandom().nextObject(RemoveItemInputPayload.class);
        ResponseEntity<Void> response = ResponseEntity.ok().build();
        Mockito.when(shoppingCartServiceClient.removeItem(any())).thenReturn(response);

        // act
        BaseFileOutput execute = removeItemCommandExecutor.execute(payload);

        // assert
        assertTrue(execute.isResult());
        Mockito.verify(shoppingCartServiceClient).removeItem(any());
    }

    @Test
    void testExecute_whenRequestIsError() {
        // arrange
        RemoveItemInputPayload payload = new EasyRandom().nextObject(RemoveItemInputPayload.class);
        ResponseEntity<Void> response = ResponseEntity.badRequest().build();
        Mockito.when(shoppingCartServiceClient.removeItem(any())).thenReturn(response);

        // act
        BaseFileOutput execute = removeItemCommandExecutor.execute(payload);

        // assert
        assertFalse(execute.isResult());
        Mockito.verify(shoppingCartServiceClient).removeItem(any());
    }

    @Test
    void testGetCommandType() {
        // act
        ShoppingCartCommandType actual = removeItemCommandExecutor.getCommandType();

        // assert
        Assertions.assertEquals(ShoppingCartCommandType.REMOVE_ITEM, actual);
    }
}
