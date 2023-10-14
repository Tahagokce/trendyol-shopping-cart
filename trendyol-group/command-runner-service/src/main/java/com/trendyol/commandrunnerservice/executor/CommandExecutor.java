package com.trendyol.commandrunnerservice.executor;

import com.trendyol.core.model.output.BaseFileOutput;

public interface CommandExecutor<R extends BaseFileOutput, I, T> {
    R execute(I input);
    T getCommandType();
}
