package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.core.model.output.BaseFileOutput;
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

@ExtendWith(MockitoExtension.class)
class ResetCartCommandExecutorTest {

    @InjectMocks
    private ResetCartCommandExecutor resetCartCommandExecutor;

    @Mock
    private ShoppingCartServiceClient shoppingCartServiceClient;

    @Test
    void testExecute() {
        // arrange
        ResponseEntity<Void> response = ResponseEntity.ok().build();
        Mockito.when(shoppingCartServiceClient.resetCart()).thenReturn(response);

        // act
        BaseFileOutput execute = resetCartCommandExecutor.execute(null);

        // assert
        assertTrue(execute.isResult());
        Mockito.verify(shoppingCartServiceClient).resetCart();
    }

    @Test
    void testExecute_whenRequestIsError() {
        // arrange
        ResponseEntity<Void> response = ResponseEntity.badRequest().build();
        Mockito.when(shoppingCartServiceClient.resetCart()).thenReturn(response);

        // act
        BaseFileOutput execute = resetCartCommandExecutor.execute(null);

        // assert
        assertFalse(execute.isResult());
        Mockito.verify(shoppingCartServiceClient).resetCart();
    }

    @Test
    void testGetCommandType() {
        // act
        ShoppingCartCommandType actual = resetCartCommandExecutor.getCommandType();

        // assert
        Assertions.assertEquals(ShoppingCartCommandType.RESET_CART, actual);
    }
}
