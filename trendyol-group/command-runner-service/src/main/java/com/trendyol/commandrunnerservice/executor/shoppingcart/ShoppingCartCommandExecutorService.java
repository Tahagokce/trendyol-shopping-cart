package com.trendyol.commandrunnerservice.executor.shoppingcart;

import com.trendyol.commandrunnerservice.executor.CommandExecutor;
import com.trendyol.common.enums.ShoppingCartCommandType;
import com.trendyol.core.model.output.BaseFileOutput;

public interface ShoppingCartCommandExecutorService extends CommandExecutor<BaseFileOutput, Object, ShoppingCartCommandType> {
}
