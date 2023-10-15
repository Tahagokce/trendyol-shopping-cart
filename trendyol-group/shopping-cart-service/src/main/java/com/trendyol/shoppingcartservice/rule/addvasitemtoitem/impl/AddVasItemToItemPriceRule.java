package com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.AddVasItemToItemRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddVasItemToItemPriceRule implements AddVasItemToItemRule {
    @Override
    public ValidationMessageResource isValid(VasItemDocument vasItem, CartItemDocument cartItem) {
        if (cartItem.getPrice() > vasItem.getPrice()){
            return ValidationMessageResource.buildSuccess();
        }else {
            return ValidationMessageResource.buildError("VasItem's price cannot be higher than Item price");
        }
    }

    @Override
    public int order() {
        return 2;
    }
}
