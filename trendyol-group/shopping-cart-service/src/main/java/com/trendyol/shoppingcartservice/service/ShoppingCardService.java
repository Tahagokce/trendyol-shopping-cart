package com.trendyol.shoppingcartservice.service;

import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;

public interface ShoppingCardService {
    void addItem(CartItemDocument cartItem);

    void addVasItemToItem(VasItemDocument vasItem);

    void removeItem(Long itemId);

    void resetCart();

    CartDocument displayCart();
}
