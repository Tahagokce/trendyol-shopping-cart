package com.trendyol.commandrunnerservice.executor.shoppingcart.factory;

import com.trendyol.commandrunnerservice.executor.CommandExecutor;
import com.trendyol.commandrunnerservice.executor.shoppingcart.ShoppingCartCommandExecutorService;
import com.trendyol.common.constant.ApplicationConstant;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.core.model.input.BaseFileInputPayload;
import com.trendyol.core.model.output.BaseFileOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShoppingCartCommandExecutorFactory {

    private final Map<ShoppingCartCommandType, ShoppingCartCommandExecutorService> commandExecutors;

    public ShoppingCartCommandExecutorFactory(List<ShoppingCartCommandExecutorService> executorServices) {
        commandExecutors = executorServices.stream().collect(Collectors.toMap(CommandExecutor::getCommandType, value -> value));
    }

    public BaseFileOutput getResult(ShoppingCartCommandType commandType, BaseFileInputPayload baseFileInputPayload) {
        ShoppingCartCommandExecutorService executorService = commandExecutors.get(commandType);
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), "getResult", "The command is being executed. Command: " + commandType.getCommand());
        BaseFileOutput result = executorService.execute(baseFileInputPayload);
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), "getResult", "Command execute successful.");
        return result;
    }
}
