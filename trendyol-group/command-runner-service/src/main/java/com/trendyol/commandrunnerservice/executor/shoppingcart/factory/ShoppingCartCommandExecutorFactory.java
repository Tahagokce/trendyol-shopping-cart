package com.trendyol.commandrunnerservice.executor.shoppingcart.factory;

import com.trendyol.commandrunnerservice.executor.CommandExecutor;
import com.trendyol.commandrunnerservice.executor.shoppingcart.ShoppingCartCommandExecutorService;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.core.model.input.BaseFileInputPayload;
import com.trendyol.core.model.output.BaseFileOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShoppingCartCommandExecutorFactory {

    private final Map<ShoppingCartCommandType, ShoppingCartCommandExecutorService> commandExecutors;

    public ShoppingCartCommandExecutorFactory(List<ShoppingCartCommandExecutorService> executorServices) {
        commandExecutors = executorServices.stream().collect(Collectors.toMap(CommandExecutor::getCommandType, value -> value));
    }

    public BaseFileOutput getResult(ShoppingCartCommandType commandType, BaseFileInputPayload baseFileInputPayload) {
        ShoppingCartCommandExecutorService executorService = commandExecutors.get(commandType);
        return executorService.execute(baseFileInputPayload);
    }
}
