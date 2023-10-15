package com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.AddVasItemToItemRule;
import org.springframework.stereotype.Service;

@Service
public class AddVasItemToItemMaxQuantityRule implements AddVasItemToItemRule {
    @Override
    public ValidationMessageResource isValid(VasItemDocument vasItem, CartItemDocument cartItem) {
        if (vasItem.getQuantity() <= cartItem.getQuantity() * 3){
            return ValidationMessageResource.buildSuccess();
        }else {
            return ValidationMessageResource
                    .buildError("A maximum of 3 vas items can be added to each Item. (VasItemQuantity <= ItemQuantity * 3)");
        }
    }

    @Override
    public int order() {
        return 3;
    }
}
