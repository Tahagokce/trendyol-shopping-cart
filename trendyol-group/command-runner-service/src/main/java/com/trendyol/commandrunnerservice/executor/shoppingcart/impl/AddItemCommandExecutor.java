package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.commandrunnerservice.executor.shoppingcart.ShoppingCartCommandExecutorService;
import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.model.input.cart.AddItemInputPayload;
import com.trendyol.common.model.request.shoppingcart.AddItemRequest;
import com.trendyol.core.model.output.BaseFileOutput;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddItemCommandExecutor implements ShoppingCartCommandExecutorService {

    private final ShoppingCartServiceClient shoppingCartServiceClient;

    @Override
    public BaseFileOutput execute(Object input) {
        AddItemInputPayload payload = (AddItemInputPayload) input;
        try {
            ResponseEntity<Void> response = shoppingCartServiceClient.addItem(prepareRequest(payload));

            return response.getStatusCode().isError()
                    ? BaseFileOutput.error()
                    : BaseFileOutput.success();
        } catch (FeignException e) {
            return BaseFileOutput.error(e.getMessage());
        }
    }

    @Override
    public ShoppingCartCommandType getCommandType() {
        return ShoppingCartCommandType.ADD_ITEM;
    }

    private AddItemRequest prepareRequest(AddItemInputPayload payload) {
        AddItemRequest request = new AddItemRequest();
        request.setItemId(payload.getItemId());
        request.setCategoryId(payload.getCategoryId());
        request.setSellerId(payload.getSellerId());
        request.setPrice(payload.getPrice());
        request.setQuantity(payload.getQuantity());
        return request;
    }
}
