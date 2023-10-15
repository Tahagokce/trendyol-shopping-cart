package com.trendyol.shoppingcartservice.rule.additem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.shoppingcartservice.rule.additem.AddItemRule;
import org.springframework.stereotype.Service;

@Service
public class AddItemMaximumCountRule implements AddItemRule {
    @Override
    public ValidationMessageResource isValid(CartDocument object) {
         if (object.getItems().stream().mapToInt(CartItemDocument::getQuantity).sum() <= 30){
             return ValidationMessageResource.buildSuccess();
         }else {
             return ValidationMessageResource.buildError("The total of products cannot exceed 30");
         }

    }

    @Override
    public int order() {
        return 1;
    }
}
