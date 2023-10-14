package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.commandrunnerservice.executor.shoppingcart.ShoppingCartCommandExecutorService;
import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.model.resource.CartResource;
import com.trendyol.common.util.JsonUtil;
import com.trendyol.core.model.output.BaseFileOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisplayCartCommandExecutor implements ShoppingCartCommandExecutorService {

    private final ShoppingCartServiceClient shoppingCartServiceClient;

    @Override
    public BaseFileOutput execute(Object input) {
        ResponseEntity<CartResource> response = shoppingCartServiceClient.displayCart();

        return response.getStatusCode().isError()
                ? BaseFileOutput.error()
                : BaseFileOutput.of(true, JsonUtil.toString(response.getBody()));
    }

    @Override
    public ShoppingCartCommandType getCommandType() {
        return ShoppingCartCommandType.DISPLAY_CART;
    }
}
