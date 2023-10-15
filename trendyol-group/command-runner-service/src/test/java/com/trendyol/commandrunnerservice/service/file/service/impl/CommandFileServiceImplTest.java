package com.trendyol.commandrunnerservice.service.file.service.impl;

import com.google.gson.JsonObject;
import com.trendyol.common.exception.CommandFileReadException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CommandFileServiceImplTest {

    @InjectMocks
    private CommandFileServiceImpl commandFileService;

    private Path fullFilePath;
    private final String strJson = "{\"command\":\"displayCart\"}";

    @BeforeEach
    void setUp() throws IOException {
        fullFilePath = Files.createTempFile("tmp", null);
        Files.writeString(fullFilePath, strJson);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(fullFilePath);
    }

    @Test
    void testRead() {
        // act
        List<JsonObject> actual = commandFileService.read("", fullFilePath.toFile().getPath());

        // assert
        Assertions.assertEquals(strJson, actual.get(0).toString());
    }

    @Test
    void testRead_whenIfTheCommandToBeReadIsCorrupt() {
        // act
        Executable actual = () -> commandFileService.read("tstxxyy1", "..ess");

        // assert
        Assertions.assertThrows(CommandFileReadException.class, actual);
    }

    @Test
    void testWrite() {
        // act
        commandFileService.write("", fullFilePath.toFile().getPath(), strJson);

        // assert
        Assertions.assertTrue(true);
    }

    @Test
    void testWrite_whenPathNotFound() {
        // act
        Executable actual = () -> commandFileService.write("", "", strJson);

        // assert
        Assertions.assertThrows(CommandFileReadException.class, actual);
    }
}
