package com.trendyol.shoppingcartservice.card.converter;

import com.trendyol.common.model.request.shoppingcart.AddVasItemToItemRequest;
import com.trendyol.common.model.resource.VasItemResource;
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

    public VasItemResource toResource(VasItemDocument document){
        VasItemResource resource = new VasItemResource();
        resource.setVasItemId(document.getVasItemId());
        resource.setVasCategoryId(document.getCategoryId());
        resource.setVasSellerId(document.getSellerId());
        resource.setPrice(document.getPrice());
        resource.setQuantity(document.getQuantity());
        return resource;
    }
}
