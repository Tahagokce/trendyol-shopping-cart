package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.commandrunnerservice.executor.shoppingcart.ShoppingCartCommandExecutorService;
import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.model.input.cart.RemoveItemInputPayload;
import com.trendyol.common.model.request.shoppingcart.RemoveItemRequest;
import com.trendyol.core.model.output.BaseFileOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveItemCommandExecutor implements ShoppingCartCommandExecutorService {

    private final ShoppingCartServiceClient shoppingCartServiceClient;

    @Override
    public BaseFileOutput execute(Object input) {
        RemoveItemInputPayload payload = (RemoveItemInputPayload) input;
        ResponseEntity<Void> response = shoppingCartServiceClient.removeItem(prepareRequest(payload));

        return response.getStatusCode().isError()
                ? BaseFileOutput.error()
                : BaseFileOutput.success();
    }

    @Override
    public ShoppingCartCommandType getCommandType() {
        return ShoppingCartCommandType.REMOVE_ITEM;
    }

    private RemoveItemRequest prepareRequest(RemoveItemInputPayload payload){
        RemoveItemRequest request = new RemoveItemRequest();
        request.setItemId(payload.getItemId());
        return request;
    }
}
