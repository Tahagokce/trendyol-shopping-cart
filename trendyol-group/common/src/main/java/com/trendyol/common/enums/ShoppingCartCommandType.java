package com.trendyol.common.enums;

import com.trendyol.common.model.input.cart.AddItemInputPayload;
import com.trendyol.common.model.input.cart.AddVasItemToItemInputPayload;
import com.trendyol.common.model.input.cart.RemoveItemInputPayload;
import com.trendyol.core.model.input.BaseFileInputPayload;
import lombok.Getter;

@Getter
public enum ShoppingCartCommandType {

    ADD_ITEM("addItem", AddItemInputPayload.class),
    ADD_VAS_ITEM_TO_ITEM("addVasItemToItem", AddVasItemToItemInputPayload.class),
    REMOVE_ITEM("removeItem", RemoveItemInputPayload.class),
    RESET_CART("resetCart", BaseFileInputPayload.class),
    DISPLAY_CART("displayCart", BaseFileInputPayload.class);

    private final String command;

    private final Class<? extends BaseFileInputPayload> clazz;

    ShoppingCartCommandType(String command, Class<? extends BaseFileInputPayload> clazz) {
        this.command = command;
        this.clazz = clazz;
    }

    public static ShoppingCartCommandType valueOfCommand(String command) {
        for (ShoppingCartCommandType e : values()) {
            if (e.command.equals(command)) {
                return e;
            }
        }
        return null;
    }
}
