package com.trendyol.commandrunnerservice.executor.shoppingcart.impl;

import com.trendyol.commandrunnerservice.executor.shoppingcart.ShoppingCartCommandExecutorService;
import com.trendyol.common.client.ShoppingCartServiceClient;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.model.input.cart.AddVasItemToItemInputPayload;
import com.trendyol.common.model.request.shoppingcart.AddVasItemToItemRequest;
import com.trendyol.core.model.output.BaseFileOutput;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddVasItemToItemCommandExecutor implements ShoppingCartCommandExecutorService {

    private final ShoppingCartServiceClient shoppingCartServiceClient;

    @Override
    public BaseFileOutput execute(Object input) {
        AddVasItemToItemInputPayload payload = (AddVasItemToItemInputPayload) input;
        try {
            ResponseEntity<Void> response = shoppingCartServiceClient.addVasItemToItem(prepareRequest(payload));

            return response.getStatusCode().isError()
                    ? BaseFileOutput.error()
                    : BaseFileOutput.success();
        } catch (FeignException e) {
            return BaseFileOutput.error(e.getMessage());
        }
    }

    @Override
    public ShoppingCartCommandType getCommandType() {
        return ShoppingCartCommandType.ADD_VAS_ITEM_TO_ITEM;
    }

    private AddVasItemToItemRequest prepareRequest(AddVasItemToItemInputPayload payload) {
        AddVasItemToItemRequest request = new AddVasItemToItemRequest();
        request.setItemId(payload.getItemId());
        request.setVasItemId(payload.getVasItemId());
        request.setVasCategoryId(payload.getVasCategoryId());
        request.setVasSellerId(payload.getVasSellerId());
        request.setPrice(payload.getPrice());
        request.setQuantity(payload.getQuantity());
        return request;
    }
}
