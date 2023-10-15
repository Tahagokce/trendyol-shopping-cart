package com.trendyol.common.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class JsonUtilTest {

    @Test
    void testToString() {
        // arrange
        Object mockObj = new EasyRandom().nextObject(Object.class);

        // act
        String actual = JsonUtil.toString(mockObj);

        // assert
        assertNotNull(actual);
    }

    @Test
    void testToObject() {
        // arrange
        JsonElement jsonElement = new JsonObject();

        // act
        JsonElement actual = JsonUtil.toObject(jsonElement, JsonElement.class);

        // assert
        assertNotNull(actual);
    }
}
