package com.trendyol.shoppingcartservice.rule.additem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.shoppingcartservice.rule.additem.AddItemRule;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AddItemUniqueMaximumCountRule implements AddItemRule {

    @Override
    public ValidationMessageResource isValid(CartDocument object) {
        Set<CartItemDocument> cartItem = new HashSet<>(object.getItems());

        if (cartItem.size() <= 10){
            return ValidationMessageResource.buildSuccess();
        }else {
            return ValidationMessageResource.buildError("Maximum number of unique products is 10");
        }
    }

    @Override
    public int order() {
        return 0;
    }
}
