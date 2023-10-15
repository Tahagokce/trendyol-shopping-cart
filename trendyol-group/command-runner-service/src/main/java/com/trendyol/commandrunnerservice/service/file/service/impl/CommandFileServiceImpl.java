package com.trendyol.commandrunnerservice.service.file.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.trendyol.commandrunnerservice.service.file.service.CommandFileService;
import com.trendyol.common.exception.CommandFileReadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandFileServiceImpl implements CommandFileService {

    public List<JsonObject> read(String filePath, String fileName) {
        Path path = Path.of(filePath, fileName);

        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(line -> JsonParser.parseString(line).getAsJsonObject()).toList();
        } catch (IOException e) {
            log.error("Make sure that you wrote the file line by line and that the commands and objects are correct. Exception Message : {}", e.getMessage());
            throw new CommandFileReadException(e.getMessage());
        }
    }

    @Override
    public void write(String filePath, String fileName, String output) {
        Path path = Path.of(filePath, fileName);
        try {
            Files.writeString(path, output);
        } catch (IOException e) {
            log.error("An error was received while creating the file. Exception Message : {}", e.getMessage());
            throw new CommandFileReadException(e.getMessage());
        }
    }
}
