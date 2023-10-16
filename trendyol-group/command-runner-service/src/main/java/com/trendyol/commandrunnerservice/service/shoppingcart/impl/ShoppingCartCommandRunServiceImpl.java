package com.trendyol.commandrunnerservice.service.shoppingcart.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.trendyol.commandrunnerservice.executor.shoppingcart.factory.ShoppingCartCommandExecutorFactory;
import com.trendyol.commandrunnerservice.service.file.service.CommandFileService;
import com.trendyol.commandrunnerservice.service.shoppingcart.ShoppingCartCommandRunService;
import com.trendyol.common.constant.ApplicationConstant;
import com.trendyol.common.constant.FileInputConstants;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.util.JsonUtil;
import com.trendyol.core.model.dto.ShoppingCartFileOutputDto;
import com.trendyol.core.model.input.BaseFileInputPayload;
import com.trendyol.core.model.output.BaseFileOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShoppingCartCommandRunServiceImpl implements ShoppingCartCommandRunService {

    @Value("${spcloud.shopping-cart-task.input-file-path}")
    private String inputFilePath;

    @Value("${spcloud.shopping-cart-task.input-file-name}")
    private String inputFileName;

    @Value("${spcloud.shopping-cart-task.output-file-path}")
    private String outputFilePath;

    @Value("${spcloud.shopping-cart-task.output-file-name}")
    private String outputFileName;

    private final ShoppingCartCommandExecutorFactory shoppingCartCommandExecutorFactory;

    private final CommandFileService commandFileService;

    @Override
    public void run() {
        List<JsonObject> inputs = commandFileService.read(inputFilePath, inputFileName);
        String output = inputs.stream().map(this::process)
                .map(ShoppingCartFileOutputDto::getOutput)
                .map(JsonUtil::toString)
                .collect(Collectors.joining(System.lineSeparator()));

        commandFileService.write(outputFilePath, outputFileName, output);

    }

    private ShoppingCartFileOutputDto process(JsonObject input) {
        String methodName = "process";
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), methodName, "Command Execution process started...");
        String command = input.get(FileInputConstants.COMMAND).getAsString();

        if (StringUtils.isBlank(command)) {
            log.error(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), methodName, "Incorrect command entry");
            return ShoppingCartFileOutputDto.of(FileInputConstants.ERROR, BaseFileOutput.error());
        }

        ShoppingCartCommandType commandType = ShoppingCartCommandType.valueOfCommand(command);

        JsonElement jsonElement = input.get(FileInputConstants.PAYLOAD);
        BaseFileInputPayload payload = JsonUtil.toObject(jsonElement, Objects.requireNonNull(commandType).getClazz());

        BaseFileOutput output = shoppingCartCommandExecutorFactory.getResult(commandType, payload);
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), methodName, "Command Execution process completed successful");
        return ShoppingCartFileOutputDto.of(commandType.getCommand(), output);
    }

}
