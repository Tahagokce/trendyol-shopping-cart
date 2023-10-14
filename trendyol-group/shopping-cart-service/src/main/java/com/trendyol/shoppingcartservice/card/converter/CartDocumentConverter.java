package com.trendyol.shoppingcartservice.card.converter;

import com.trendyol.common.model.resource.CartResource;
import com.trendyol.entity.db.cart.Cart;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.entity.document.cart.CartItemDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartDocumentConverter {

    private final CartItemDocumentConverter cartItemDocumentConverter;

    public CartDocument toCartDocument(Cart entity){
        CartDocument document = new CartDocument();
        document.setCartId(entity.getId());
        document.setUserId(entity.getUser().getId());
        document.setId(entity.getUser().getId());
        List<CartItemDocument> itemDocuments = entity.getItems().stream().map(cartItemDocumentConverter::toDocument).toList();
        document.setItems(itemDocuments);
        return document;
    }

    public CartResource toResource(CartDocument document){
        CartResource resource = new CartResource();
        resource.setTotalAmount(document.getTotalAmount());
        resource.setAppliedPromotionId(document.getAppliedPromotionId());
        resource.setTotalDiscount(resource.getTotalDiscount());
        resource.setItems(document.getItems().stream().map(cartItemDocumentConverter::toResource).toList());
        return resource;
    }
}
