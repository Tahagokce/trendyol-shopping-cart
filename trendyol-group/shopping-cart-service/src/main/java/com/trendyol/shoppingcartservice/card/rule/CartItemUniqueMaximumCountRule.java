package com.trendyol.shoppingcartservice.card.rule;

import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartItemUniqueMaximumCountRule implements CartRule {

    @Override
    public boolean isValid(CartDocument object) {
        Set<CartItemDocument> cartItem = new HashSet<>(object.getItems());
        return cartItem.size() <= 10;
    }

    @Override
    public int order() {
        return 0;
    }
}
