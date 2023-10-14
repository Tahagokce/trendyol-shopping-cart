package com.trendyol.shoppingcartservice.card.service;

import com.trendyol.entity.document.cart.CartDocument;


public interface CartService {
    void save(CartDocument cart);

    CartDocument findByUserId(Long userId);
}
