package com.trendyol.shoppingcartservice.rule.addvasitemtoitem;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;

public interface AddVasItemToItemRule {
    ValidationMessageResource isValid(VasItemDocument vasItem, CartItemDocument cartItem);
    int order();
}
