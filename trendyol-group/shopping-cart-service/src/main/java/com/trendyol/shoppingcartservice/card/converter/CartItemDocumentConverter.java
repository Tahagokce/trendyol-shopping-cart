package com.trendyol.shoppingcartservice.card.converter;

import com.trendyol.common.model.request.shoppingcart.AddItemRequest;
import com.trendyol.common.model.resource.CartItemResource;
import com.trendyol.entity.db.cart.CartItem;
import com.trendyol.entity.document.cart.CartItemDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemDocumentConverter {
    private final VasItemConverter vasItemConverter;

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

    public CartItemResource toResource(CartItemDocument document){
        CartItemResource resource = new CartItemResource();
        resource.setItemId(document.getItemId());
        resource.setCategoryId(document.getCategoryId());
        resource.setSellerId(document.getSellerId());
        resource.setPrice(document.getPrice());
        resource.setQuantity(document.getQuantity());
        resource.setVasItems(document.getVasItems().stream().map(vasItemConverter::toResource).toList());
        return resource;
    }
}
