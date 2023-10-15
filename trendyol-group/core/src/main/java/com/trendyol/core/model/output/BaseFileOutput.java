package com.trendyol.core.model.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor(staticName = "of")
public class BaseFileOutput implements Serializable {
    private boolean result;
    private String message;

    public static BaseFileOutput success() {
        return BaseFileOutput.of(true, "success");
    }

    public static BaseFileOutput error() {
        return BaseFileOutput.error("error");
    }

    public static BaseFileOutput error(String message) {
        return BaseFileOutput.of(false, message);
    }
}
