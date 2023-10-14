package com.trendyol.shoppingcartservice.card.rule;

import com.trendyol.entity.document.cart.CartDocument;
import org.springframework.stereotype.Service;

@Service
public class CartItemMaximumCountRule implements CartRule {
    @Override
    public boolean isValid(CartDocument object) {
        return object.getItems().size() <= 30;
    }

    @Override
    public int order() {
        return 1;
    }
}
