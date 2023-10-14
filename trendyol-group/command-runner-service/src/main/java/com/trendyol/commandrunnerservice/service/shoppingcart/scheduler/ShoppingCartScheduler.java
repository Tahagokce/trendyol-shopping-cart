package com.trendyol.commandrunnerservice.service.shoppingcart.scheduler;

import com.trendyol.commandrunnerservice.service.shoppingcart.ShoppingCartCommandRunService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingCartScheduler {

    private final ShoppingCartCommandRunService shoppingCartCommandRunService;

    @Scheduled(cron = "${spcloud.shopping-cart-task.scheduler-cron}")
    public void runCartCommands(){
        shoppingCartCommandRunService.run();
    }
}
