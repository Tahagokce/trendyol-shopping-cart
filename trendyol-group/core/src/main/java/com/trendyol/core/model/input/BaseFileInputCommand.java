package com.trendyol.core.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseFileInputCommand<T extends BaseFileInputPayload & Serializable> implements Serializable {
    @NotBlank
    private String command;

    private T payload;
}
