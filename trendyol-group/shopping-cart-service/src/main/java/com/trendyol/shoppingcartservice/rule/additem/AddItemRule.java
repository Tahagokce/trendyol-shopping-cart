package com.trendyol.shoppingcartservice.rule.additem;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;

public interface AddItemRule {
    ValidationMessageResource isValid(CartDocument cart);
    int order();
}
