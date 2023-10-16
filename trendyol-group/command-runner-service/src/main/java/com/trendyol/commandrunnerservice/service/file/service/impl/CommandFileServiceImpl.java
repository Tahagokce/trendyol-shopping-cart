package com.trendyol.commandrunnerservice.service.file.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.trendyol.commandrunnerservice.service.file.service.CommandFileService;
import com.trendyol.common.constant.ApplicationConstant;
import com.trendyol.common.exception.CommandFileReadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandFileServiceImpl implements CommandFileService {

    public List<JsonObject> read(String filePath, String fileName) {
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), "read", String.format("Reading file, File path: %s File name: %s", filePath, fileName));
        Path path = Path.of(filePath, fileName);
        try (Stream<String> lines = Files.lines(path)) {
            List<JsonObject> jsonObjects = lines.map(line -> JsonParser.parseString(line).getAsJsonObject()).toList();
            log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), "read", "Read file successful");
            return jsonObjects;
        } catch (IOException e) {
            log.error("Make sure that you wrote the file line by line and that the commands and objects are correct.");
            throw new CommandFileReadException(e.getMessage());
        }
    }

    @Override
    public void write(String filePath, String fileName, String output) {
        Path path = Path.of(filePath, fileName);
        log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), "write", String.format("Writing file, File path: %s File name: %s", filePath, fileName));
        try {
            Files.write(path, output.getBytes(), StandardOpenOption.APPEND);
            log.info(ApplicationConstant.GLOBAL_LOG_INFO_PATTERN, getClass().getSimpleName(), "write", "Write file successful");
        } catch (IOException e) {
            log.error("An error was received while creating the file.");
            throw new CommandFileReadException(e.getMessage());
        }
    }
}
