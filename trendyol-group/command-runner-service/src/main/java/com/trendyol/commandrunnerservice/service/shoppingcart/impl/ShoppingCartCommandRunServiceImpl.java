package com.trendyol.commandrunnerservice.service.shoppingcart.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.trendyol.commandrunnerservice.executor.shoppingcart.factory.ShoppingCartCommandExecutorFactory;
import com.trendyol.commandrunnerservice.service.file.service.CommandFileService;
import com.trendyol.commandrunnerservice.service.shoppingcart.ShoppingCartCommandRunService;
import com.trendyol.common.constant.FileInputConstants;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.common.util.JsonUtil;
import com.trendyol.core.model.dto.ShoppingCartFileOutputDto;
import com.trendyol.core.model.input.BaseFileInputPayload;
import com.trendyol.core.model.output.BaseFileOutput;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ShoppingCartCommandRunServiceImpl implements ShoppingCartCommandRunService {

    @Value("${spcloud.shopping-cart-task.input-file-path}")
    private String inputFilePath;

    @Value("${spcloud.shopping-cart-task.input-file-name}")
    private String inputFileName;

    @Value("${spcloud.shopping-cart-task.output-file-path}")
    private String outputFilePath;

    private final ShoppingCartCommandExecutorFactory shoppingCartCommandExecutorFactory;

    private final CommandFileService commandFileService;

    @Override
    public void run() {
        List<JsonObject> inputs = commandFileService.read(inputFilePath, inputFileName);
        inputs.stream().map(this::process)
                .forEach(
                        result -> commandFileService.write(
                                outputFilePath
                                , result.getFileName()
                                , JsonUtil.toString(result.getOutput())
                        )
                );
    }

    private ShoppingCartFileOutputDto process(JsonObject input) {
        String command = input.get(FileInputConstants.COMMAND).getAsString();

        if (StringUtils.isBlank(command)) {
            return ShoppingCartFileOutputDto.of(FileInputConstants.ERROR, BaseFileOutput.error());
        }

        ShoppingCartCommandType commandType = ShoppingCartCommandType.valueOfCommand(command);

        JsonElement jsonElement = input.get(FileInputConstants.PAYLOAD);
        BaseFileInputPayload payload = JsonUtil.toObject(jsonElement, Objects.requireNonNull(commandType).getClazz());

        BaseFileOutput output = shoppingCartCommandExecutorFactory.getResult(commandType, payload);
        return ShoppingCartFileOutputDto.of(commandType.getCommand(), output);
    }

}
