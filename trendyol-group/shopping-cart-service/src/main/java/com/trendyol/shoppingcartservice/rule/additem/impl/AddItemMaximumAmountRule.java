package com.trendyol.shoppingcartservice.rule.additem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.shoppingcartservice.rule.additem.AddItemRule;
import org.springframework.stereotype.Service;

@Service
public class AddItemMaximumAmountRule implements AddItemRule {
    @Override
    public ValidationMessageResource isValid(CartDocument cart) {
        if (cart.getTotalAmount() <= 500000){
            return ValidationMessageResource.buildSuccess();
        }else {
            return ValidationMessageResource.buildError("Total amount cannot be 500000");
        }
    }

    @Override
    public int order() {
        return 2;
    }
}
