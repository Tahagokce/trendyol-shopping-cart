package com.trendyol.common.client;

import com.trendyol.common.enpoints.ShoppingCartEndpoints;
import com.trendyol.common.model.request.shoppingcart.AddItemRequest;
import com.trendyol.common.model.request.shoppingcart.AddVasItemToItemRequest;
import com.trendyol.common.model.request.shoppingcart.RemoveItemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shopping-cart-service", path = ShoppingCartEndpoints.SHOPPING_CART)
public interface ShoppingCartServiceClient {

    @PutMapping(ShoppingCartEndpoints.ADD_ITEM)
    ResponseEntity<Void> addItem(@RequestBody AddItemRequest request);

    @PutMapping(ShoppingCartEndpoints.ADD_VAS_ITEM_TO_ITEM)
    ResponseEntity<Void> addVasItemToItem(@RequestBody AddVasItemToItemRequest request);

    @DeleteMapping(ShoppingCartEndpoints.REMOVE_ITEM)
    ResponseEntity<Void> removeItem(@RequestBody RemoveItemRequest request);

    @DeleteMapping(ShoppingCartEndpoints.RESET_CART)
    ResponseEntity<Void> resetCart();

    @GetMapping
    ResponseEntity<Void> findCart();
}
