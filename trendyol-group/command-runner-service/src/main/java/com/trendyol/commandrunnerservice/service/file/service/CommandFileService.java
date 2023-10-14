package com.trendyol.commandrunnerservice.service.file.service;

import com.google.gson.JsonObject;

import java.util.List;

public interface CommandFileService {
    List<JsonObject> read(String filePath, String fileName);
    void write(String filePath, String fileName, String outputs);
}
