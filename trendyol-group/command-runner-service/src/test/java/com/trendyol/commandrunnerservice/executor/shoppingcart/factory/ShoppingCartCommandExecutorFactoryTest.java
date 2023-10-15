package com.trendyol.commandrunnerservice.executor.shoppingcart.factory;

import com.trendyol.commandrunnerservice.executor.shoppingcart.ShoppingCartCommandExecutorService;
import com.trendyol.commandrunnerservice.executor.shoppingcart.impl.AddItemCommandExecutor;
import com.trendyol.commandrunnerservice.executor.shoppingcart.impl.AddVasItemToItemCommandExecutor;
import com.trendyol.commandrunnerservice.executor.shoppingcart.impl.DisplayCartCommandExecutor;
import com.trendyol.commandrunnerservice.executor.shoppingcart.impl.RemoveItemCommandExecutor;
import com.trendyol.commandrunnerservice.executor.shoppingcart.impl.ResetCartCommandExecutor;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.model.input.cart.RemoveItemInputPayload;
import com.trendyol.core.model.output.BaseFileOutput;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ShoppingCartCommandExecutorFactoryTest {

    private ShoppingCartCommandExecutorFactory shoppingCartCommandExecutorFactory;

    @Mock
    private AddItemCommandExecutor addItemCommandExecutor;

    @Mock
    private AddVasItemToItemCommandExecutor addVasItemToItemCommandExecutor;

    @Mock
    private DisplayCartCommandExecutor displayCartCommandExecutor;

    @Mock
    private RemoveItemCommandExecutor removeItemCommandExecutor;

    @Mock
    private ResetCartCommandExecutor resetCartCommandExecutor;

    @BeforeEach
    void setUp() {

        List<ShoppingCartCommandExecutorService> executorServices = Arrays.asList(
                addItemCommandExecutor,
                addVasItemToItemCommandExecutor,
                displayCartCommandExecutor,
                removeItemCommandExecutor,
                resetCartCommandExecutor
        );

        Mockito.when(addItemCommandExecutor.getCommandType()).thenReturn(ShoppingCartCommandType.ADD_ITEM);
        Mockito.when(addVasItemToItemCommandExecutor.getCommandType()).thenReturn(ShoppingCartCommandType.ADD_VAS_ITEM_TO_ITEM);
        Mockito.when(displayCartCommandExecutor.getCommandType()).thenReturn(ShoppingCartCommandType.DISPLAY_CART);
        Mockito.when(removeItemCommandExecutor.getCommandType()).thenReturn(ShoppingCartCommandType.REMOVE_ITEM);
        Mockito.when(resetCartCommandExecutor.getCommandType()).thenReturn(ShoppingCartCommandType.RESET_CART);

        shoppingCartCommandExecutorFactory = new ShoppingCartCommandExecutorFactory(executorServices);
    }

    @Test
    void getResult_whenCommandTypeAddItem() {
        // arrange
        RemoveItemInputPayload baseFileInputPayload = new EasyRandom().nextObject(RemoveItemInputPayload.class);

        BaseFileOutput baseFileOutput = new EasyRandom().nextObject(BaseFileOutput.class);
        Mockito.when(addItemCommandExecutor.execute(any())).thenReturn(baseFileOutput);

        // act
        BaseFileOutput actual = shoppingCartCommandExecutorFactory.getResult(ShoppingCartCommandType.ADD_ITEM, baseFileInputPayload);

        // assert
        assertNotNull(actual);
        Mockito.verify(addItemCommandExecutor).execute(any());
    }

    @Test
    void getResult_whenCommandTypeAddVasItem() {
        // arrange
        RemoveItemInputPayload baseFileInputPayload = new EasyRandom().nextObject(RemoveItemInputPayload.class);

        BaseFileOutput baseFileOutput = new EasyRandom().nextObject(BaseFileOutput.class);
        Mockito.when(addVasItemToItemCommandExecutor.execute(any())).thenReturn(baseFileOutput);

        // act
        BaseFileOutput actual = shoppingCartCommandExecutorFactory.getResult(ShoppingCartCommandType.ADD_VAS_ITEM_TO_ITEM, baseFileInputPayload);

        // assert
        assertNotNull(actual);
        Mockito.verify(addVasItemToItemCommandExecutor).execute(any());
    }

    @Test
    void getResult_whenCommandTypeDisplayCart() {
        // arrange
        RemoveItemInputPayload baseFileInputPayload = new EasyRandom().nextObject(RemoveItemInputPayload.class);

        BaseFileOutput baseFileOutput = new EasyRandom().nextObject(BaseFileOutput.class);
        Mockito.when(displayCartCommandExecutor.execute(any())).thenReturn(baseFileOutput);

        // act
        BaseFileOutput actual = shoppingCartCommandExecutorFactory.getResult(ShoppingCartCommandType.DISPLAY_CART, baseFileInputPayload);

        // assert
        assertNotNull(actual);
        Mockito.verify(displayCartCommandExecutor).execute(any());
    }

    @Test
    void getResult_whenCommandTypeRemoveItem() {
        // arrange
        RemoveItemInputPayload baseFileInputPayload = new EasyRandom().nextObject(RemoveItemInputPayload.class);

        BaseFileOutput baseFileOutput = new EasyRandom().nextObject(BaseFileOutput.class);
        Mockito.when(removeItemCommandExecutor.execute(any())).thenReturn(baseFileOutput);

        // act
        BaseFileOutput actual = shoppingCartCommandExecutorFactory.getResult(ShoppingCartCommandType.REMOVE_ITEM, baseFileInputPayload);

        // assert
        assertNotNull(actual);
        Mockito.verify(removeItemCommandExecutor).execute(any());
    }

    @Test
    void getResult_whenCommandTypeResetCart() {
        // arrange
        RemoveItemInputPayload baseFileInputPayload = new EasyRandom().nextObject(RemoveItemInputPayload.class);

        BaseFileOutput baseFileOutput = new EasyRandom().nextObject(BaseFileOutput.class);
        Mockito.when(resetCartCommandExecutor.execute(any())).thenReturn(baseFileOutput);

        // act
        BaseFileOutput actual = shoppingCartCommandExecutorFactory.getResult(ShoppingCartCommandType.RESET_CART, baseFileInputPayload);

        // assert
        assertNotNull(actual);
        Mockito.verify(resetCartCommandExecutor).execute(any());

    }
}
