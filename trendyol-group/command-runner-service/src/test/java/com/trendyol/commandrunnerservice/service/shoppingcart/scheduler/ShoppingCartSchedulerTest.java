package com.trendyol.commandrunnerservice.service.shoppingcart.scheduler;

import com.trendyol.commandrunnerservice.service.shoppingcart.ShoppingCartCommandRunService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ShoppingCartSchedulerTest {

    @InjectMocks
    private ShoppingCartScheduler shoppingCartScheduler;

    @Mock
    private ShoppingCartCommandRunService shoppingCartCommandRunService;


    @Test
    void testRunCartCommands() {
        // act
        shoppingCartScheduler.runCartCommands();

        // assert
        assertTrue(true);
        Mockito.verify(shoppingCartCommandRunService).run();
    }
}
