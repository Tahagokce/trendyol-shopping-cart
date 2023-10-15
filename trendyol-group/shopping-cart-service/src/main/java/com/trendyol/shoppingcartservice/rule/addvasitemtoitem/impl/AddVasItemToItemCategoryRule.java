package com.trendyol.shoppingcartservice.rule.addvasitemtoitem.impl;

import com.trendyol.common.model.resource.ValidationMessageResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.rule.addvasitemtoitem.AddVasItemToItemRule;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddVasItemToItemCategoryRule implements AddVasItemToItemRule {
    @Override
    public ValidationMessageResource isValid(VasItemDocument vasItem, CartItemDocument cartItem) {
        if (Objects.equals(vasItem.getCategoryId(), 3442L)){
            return ValidationMessageResource.buildSuccess();
        }else{
            return ValidationMessageResource.buildError("Vas Item's category id must be 3442");
        }
    }

    @Override
    public int order() {
        return 1;
    }
}
