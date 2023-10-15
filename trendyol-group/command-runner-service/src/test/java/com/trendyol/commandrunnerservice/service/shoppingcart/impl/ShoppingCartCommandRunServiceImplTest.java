package com.trendyol.commandrunnerservice.service.shoppingcart.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.trendyol.commandrunnerservice.executor.shoppingcart.factory.ShoppingCartCommandExecutorFactory;
import com.trendyol.commandrunnerservice.service.file.service.CommandFileService;
import com.trendyol.core.model.output.BaseFileOutput;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ShoppingCartCommandRunServiceImplTest {

    @InjectMocks
    private ShoppingCartCommandRunServiceImpl shoppingCartCommandRunService;

    @Mock
    private ShoppingCartCommandExecutorFactory shoppingCartCommandExecutorFactory;

    @Mock
    private CommandFileService commandFileService;

    @Test
    void testRun() {
        // arrange
        String inputFilePath = new EasyRandom().nextObject(String.class);
        String inputFileName = new EasyRandom().nextObject(String.class);
        String outputFilePath = new EasyRandom().nextObject(String.class);

        ReflectionTestUtils.setField(shoppingCartCommandRunService, "inputFilePath", inputFilePath);
        ReflectionTestUtils.setField(shoppingCartCommandRunService, "inputFileName", inputFileName);
        ReflectionTestUtils.setField(shoppingCartCommandRunService, "outputFilePath", outputFilePath);

        List<JsonObject> jsonObjects = getCommands();
        Mockito.when(commandFileService.read(inputFilePath, inputFileName)).thenReturn(jsonObjects);

        BaseFileOutput baseFileOutput = new EasyRandom().nextObject(BaseFileOutput.class);
        Mockito.when(shoppingCartCommandExecutorFactory.getResult(any(), any())).thenReturn(baseFileOutput);

        // act
        shoppingCartCommandRunService.run();

        // assert
        Mockito.verify(commandFileService).read(inputFilePath, inputFileName);
    }

    @Test
    void testRun_whenCommandIsBlank() {
        // arrange
        String inputFilePath = new EasyRandom().nextObject(String.class);
        String inputFileName = new EasyRandom().nextObject(String.class);
        String outputFilePath = new EasyRandom().nextObject(String.class);

        ReflectionTestUtils.setField(shoppingCartCommandRunService, "inputFilePath", inputFilePath);
        ReflectionTestUtils.setField(shoppingCartCommandRunService, "inputFileName", inputFileName);
        ReflectionTestUtils.setField(shoppingCartCommandRunService, "outputFilePath", outputFilePath);

        JsonObject emptyCommand = JsonParser.parseString("{\"command\":\"\"}").getAsJsonObject();
        Mockito.when(commandFileService.read(inputFilePath, inputFileName)).thenReturn(Collections.singletonList(emptyCommand));

        // act
        shoppingCartCommandRunService.run();

        // assert
        Mockito.verify(commandFileService).read(inputFilePath, inputFileName);

    }

    private List<JsonObject> getCommands() {
        return Stream.of("{\"command\":\"addItem\",\"payload\":{\"itemId\":19,\"categoryId\":3004,\"sellerId\":10,\"price\":100.0,\"quantity\":2}}",
                        "{\"command\":\"addVasItemToItem\",\"payload\":{\"itemId\":19,\"vasItemId\":99,\"vasCategoryId\":100,\"vasSellerId\":5003,\"price\":110.0,\"quantity\":1}}",
                        "{\"command\":\"displayCart\"}",
                        "{\"command\":\"removeItem\", \"payload\":{\"itemId\":19}}",
                        "{\"command\":\"resetCart\"}")
                .map(line -> JsonParser.parseString(line).getAsJsonObject())
                .toList();
    }
}
