package com.trendyol.common.enums;

import lombok.Getter;

@Getter
public enum ShoppingCartCommandType {

    ADD_ITEM("addItem"),
    ADD_VAS_ITEM_TO_ITEM("addVasItemToItem"),
    REMOVE_ITEM("removeItem"),
    RESET_CART("resetCart"),
    DISPLAY_CART("displayCart");

    private final String command;

    ShoppingCartCommandType(String command) {
        this.command = command;
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
