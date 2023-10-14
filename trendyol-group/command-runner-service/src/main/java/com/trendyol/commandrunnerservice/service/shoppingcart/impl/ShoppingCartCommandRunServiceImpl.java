package com.trendyol.commandrunnerservice.service.shoppingcart.impl;

import com.trendyol.commandrunnerservice.executor.shoppingcart.factory.ShoppingCartCommandExecutorFactory;
import com.trendyol.commandrunnerservice.service.shoppingcart.ShoppingCartCommandRunService;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.core.model.input.BaseFileInputCommand;
import com.trendyol.core.model.output.BaseFileOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartCommandRunServiceImpl implements ShoppingCartCommandRunService {
    private final ShoppingCartCommandExecutorFactory shoppingCartCommandExecutorFactory;

    @Override
    public BaseFileOutput run(BaseFileInputCommand<?> input) {
        return shoppingCartCommandExecutorFactory
                .getResult(ShoppingCartCommandType.valueOfCommand(input.getCommand()), input.getPayload());
    }
}
