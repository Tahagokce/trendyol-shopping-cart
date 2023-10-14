package com.trendyol.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Gson gson = new Gson();

    public static String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            try {
                return object.toString();
            } catch (Exception ex) {
                return "";
            }
        }
    }

    public static <T> T toObject(JsonElement objStr, Class<T> valueType) {
        return gson.fromJson(objStr, valueType);
    }
}
