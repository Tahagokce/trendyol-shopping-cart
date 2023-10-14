package com.trendyol.common.enpoints;

import com.trendyol.common.enpoints.base.BaseEndpoints;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ShoppingCartEndpoints extends BaseEndpoints {
    public static final String SHOPPING_CART = BASE + "/shopping-cart";
    public static final String ADD_ITEM = "add-item";
    public static final String ADD_VAS_ITEM_TO_ITEM = "add-vas-item-to-item";
    public static final String REMOVE_ITEM = "remove-item";
    public static final String RESET_CART = "reset";
}
