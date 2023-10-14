package com.trendyol.shoppingcartservice.card.converter;

import com.trendyol.common.model.request.shoppingcart.AddVasItemToItemRequest;
import com.trendyol.entity.document.cart.VasItemDocument;
import org.springframework.stereotype.Component;

@Component
public class VasItemConverter {
    public VasItemDocument toDocument(AddVasItemToItemRequest request){
        VasItemDocument document = new VasItemDocument();
        document.setVasItemId(request.getVasItemId());
        document.setItemId(request.getItemId());
        document.setCategoryId(request.getVasCategoryId());
        document.setSellerId(request.getVasSellerId());
        document.setPrice(request.getPrice());
        document.setQuantity(request.getQuantity());
        return document;
    }
}
