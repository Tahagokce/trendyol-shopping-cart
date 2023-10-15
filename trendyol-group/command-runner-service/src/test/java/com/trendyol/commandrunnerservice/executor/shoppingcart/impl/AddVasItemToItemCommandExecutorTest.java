package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.model.input.cart.AddVasItemToItemInputPayload;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddVasItemToItemCommandExecutorTest {

    @InjectMocks
    private AddVasItemToItemCommandExecutor addVasItemToItemCommandExecutor;

    @Mock
    private ShoppingCartServiceClient shoppingCartServiceClient;

    @Test
    void testExecute() {
        // arrange
        AddVasItemToItemInputPayload payload = new EasyRandom().nextObject(AddVasItemToItemInputPayload.class);
        ResponseEntity<Void> response = ResponseEntity.ok().build();
        Mockito.when(shoppingCartServiceClient.addVasItemToItem(any())).thenReturn(response);

        // act
        BaseFileOutput execute = addVasItemToItemCommandExecutor.execute(payload);

        // assert
        Assertions.assertEquals("success", execute.getMessage());
        Mockito.verify(shoppingCartServiceClient).addVasItemToItem(any());
    }

    @Test
    void testExecute_whenRequestIsError() {
        // arrange
        AddVasItemToItemInputPayload payload = new EasyRandom().nextObject(AddVasItemToItemInputPayload.class);
        ResponseEntity<Void> response = ResponseEntity.badRequest().build();
        Mockito.when(shoppingCartServiceClient.addVasItemToItem(any())).thenReturn(response);

        // act
        BaseFileOutput execute = addVasItemToItemCommandExecutor.execute(payload);

        // assert
        Assertions.assertEquals("error", execute.getMessage());
        Mockito.verify(shoppingCartServiceClient).addVasItemToItem(any());
    }

    @Test
    void testGetCommandType() {
        // act
        ShoppingCartCommandType actual = addVasItemToItemCommandExecutor.getCommandType();

        // assert
        Assertions.assertEquals(ShoppingCartCommandType.ADD_VAS_ITEM_TO_ITEM, actual);
    }
}
