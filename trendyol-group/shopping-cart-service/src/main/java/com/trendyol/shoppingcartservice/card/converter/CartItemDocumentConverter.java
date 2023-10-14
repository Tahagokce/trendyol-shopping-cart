package com.trendyol.shoppingcartservice.card.converter;

import com.trendyol.common.model.request.shoppingcart.AddItemRequest;
import com.trendyol.entity.db.cart.CartItem;
import com.trendyol.entity.document.cart.CartItemDocument;
import org.springframework.stereotype.Component;

@Component
public class CartItemDocumentConverter {

    public CartItemDocument toDocument(CartItem entity) {
        CartItemDocument document = new CartItemDocument();
        document.setCategoryId(entity.getCategory().getId());
        document.setSellerId(entity.getSeller().getId());
        document.setPrice(entity.getPrice());
        document.setQuantity(entity.getQuantity());
        return document;
    }

    public CartItemDocument toDocument(AddItemRequest request) {
        CartItemDocument document = new CartItemDocument();
        document.setItemId(request.getItemId());
        document.setCategoryId(request.getCategoryId());
        document.setSellerId(request.getSellerId());
        document.setPrice(request.getPrice());
        document.setQuantity(request.getQuantity());
        return document;
    }
}
