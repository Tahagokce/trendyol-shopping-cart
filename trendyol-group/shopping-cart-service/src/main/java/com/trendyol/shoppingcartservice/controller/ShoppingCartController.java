package com.trendyol.shoppingcartservice.controller;

import com.trendyol.common.enpoints.ShoppingCartEndpoints;
import com.trendyol.common.model.request.shoppingcart.AddItemRequest;
import com.trendyol.common.model.request.shoppingcart.AddVasItemToItemRequest;
import com.trendyol.common.model.request.shoppingcart.RemoveItemRequest;
import com.trendyol.common.model.resource.CartResource;
import com.trendyol.entity.document.cart.CartItemDocument;
import com.trendyol.entity.document.cart.VasItemDocument;
import com.trendyol.shoppingcartservice.card.converter.CartDocumentConverter;
import com.trendyol.shoppingcartservice.card.converter.CartItemDocumentConverter;
import com.trendyol.shoppingcartservice.card.converter.VasItemConverter;
import com.trendyol.shoppingcartservice.service.ShoppingCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ShoppingCartEndpoints.SHOPPING_CART)
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCardService shoppingCardService;
    private final CartDocumentConverter cartDocumentConverter;
    private final CartItemDocumentConverter cartItemDocumentConverter;
    private final VasItemConverter vasItemConverter;

    @PutMapping(ShoppingCartEndpoints.ADD_ITEM)
    public ResponseEntity<Void> addItem(@RequestBody @Valid AddItemRequest request) {
        CartItemDocument cartItem = cartItemDocumentConverter.toDocument(request);
        shoppingCardService.addItem(cartItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping(ShoppingCartEndpoints.ADD_VAS_ITEM_TO_ITEM)
    public ResponseEntity<Void> addVasItemToItem(@RequestBody @Valid AddVasItemToItemRequest request) {
        VasItemDocument document = vasItemConverter.toDocument(request);
        shoppingCardService.addVasItemToItem(document);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ShoppingCartEndpoints.REMOVE_ITEM)
    public ResponseEntity<Void> removeItem(@RequestBody @Valid RemoveItemRequest request) {
        shoppingCardService.removeItem(request.getItemId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ShoppingCartEndpoints.RESET_CART)
    public ResponseEntity<Void> resetCart() {
        shoppingCardService.resetCart();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CartResource> displayCart() {
        return ResponseEntity.ok(cartDocumentConverter.toResource(shoppingCardService.displayCart()));
    }
}
