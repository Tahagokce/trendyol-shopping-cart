package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.commandrunnerservice.executor.shoppingcart.ShoppingCartCommandExecutorService;
import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.core.model.output.BaseFileOutput;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetCartCommandExecutor implements ShoppingCartCommandExecutorService {

    private final ShoppingCartServiceClient shoppingCartServiceClient;

    @Override
    public BaseFileOutput execute(Object input) {
        try {
            ResponseEntity<Void> response = shoppingCartServiceClient.resetCart();

            return response.getStatusCode().isError()
                    ? BaseFileOutput.error()
                    : BaseFileOutput.success();
        } catch (FeignException e) {
            return BaseFileOutput.error(e.getMessage());
        }
    }

    @Override
    public ShoppingCartCommandType getCommandType() {
        return ShoppingCartCommandType.RESET_CART;
    }
}
