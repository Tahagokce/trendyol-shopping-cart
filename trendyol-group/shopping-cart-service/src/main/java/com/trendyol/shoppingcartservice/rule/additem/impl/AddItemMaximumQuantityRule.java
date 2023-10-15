package com.trendyol.shoppingcartservice.rule.additem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.shoppingcartservice.rule.additem.AddItemRule;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddItemMaximumQuantityRule implements AddItemRule {
    @Override
    public ValidationMessageResource isValid(CartDocument cart) {
        if (cart.getItems().stream().noneMatch(this::isMaximumQuantityLimit)){
            return ValidationMessageResource.buildSuccess();
        }else {
            return ValidationMessageResource.buildError("Product reached maximum quantity number");
        }
    }

    @Override
    public int order() {
        return 3;
    }

    private boolean isMaximumQuantityLimit(CartItemDocument cartItem) {
        int maxQuantity = 10;

        if (isDigitalItem(cartItem.getCategoryId())) {
            maxQuantity = 5;
        }

        return cartItem.getQuantity() > maxQuantity;
    }

    private boolean isDigitalItem(Long categoryId) {
        return Objects.equals(categoryId, 7889L);
    }
}
