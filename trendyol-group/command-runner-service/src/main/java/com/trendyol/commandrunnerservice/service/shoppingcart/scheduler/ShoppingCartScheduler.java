package com.trendyol.commandrunnerservice.service.shoppingcart.scheduler;

import com.trendyol.commandrunnerservice.service.shoppingcart.ShoppingCartCommandRunService;
import com.trendyol.common.constant.ApplicationConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShoppingCartScheduler {

    private final ShoppingCartCommandRunService shoppingCartCommandRunService;

    @Scheduled(cron = "${spcloud.shopping-cart-task.scheduler-cron}")
    public void runCartCommands() {
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, "ShoppingCartScheduler", getClass().getSimpleName(), "Shopping-cart scheduled process started.");
        shoppingCartCommandRunService.run();
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, "ShoppingCartScheduler", getClass().getSimpleName(), "Shopping-cart scheduled process completed successful.");
    }
}
