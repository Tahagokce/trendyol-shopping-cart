package com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.AddVasItemToItemRule;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddVasItemToItemSellerRule implements AddVasItemToItemRule {
    @Override
    public ValidationMessageResource isValid(VasItemDocument vasItem, CartItemDocument cartItem) {
        if (Objects.equals(vasItem.getSellerId(), 5003L)){
            return ValidationMessageResource.buildSuccess();
        }else{
            return ValidationMessageResource.buildError("Vas Item's seller id must be 5003");
        }
    }

    @Override
    public int order() {
        return 0;
    }
}
