package com.trendyol.commandrunnerservice.service.shoppingcart;

import com.trendyol.core.model.input.BaseFileInputCommand;
import com.trendyol.core.model.output.BaseFileOutput;

public interface ShoppingCartCommandRunService {
    BaseFileOutput run(BaseFileInputCommand<?> payload);
}
