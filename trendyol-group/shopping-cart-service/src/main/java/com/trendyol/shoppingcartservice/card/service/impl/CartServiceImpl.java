package com.trendyol.shoppingcartservice.card.service.impl;

import com.trendyol.entity.db.cart.Cart;
import com.trendyol.entity.db.user.User;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.shoppingcartservice.card.converter.CartDocumentConverter;
import com.trendyol.shoppingcartservice.card.repository.CartRedisDocumentRepository;
import com.trendyol.shoppingcartservice.card.repository.CartRepository;
import com.trendyol.shoppingcartservice.card.service.CartService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartRedisDocumentRepository cartRedisDocumentRepository;
    private final CartDocumentConverter cartDocumentConverter;

    @PostConstruct
    private void initializedCart(){
        CartDocument cartDocument = new CartDocument();
        cartDocument.setUserId(1L);
        cartRedisDocumentRepository.save(cartDocument);
    }

    @Override
    public void save(CartDocument cart) {
        cartRedisDocumentRepository.save(cart);
    }

    @Override
    public CartDocument findByUserId(Long userId) {
        Optional<CartDocument> optionalCartDocument = cartRedisDocumentRepository.findById(userId);

        return optionalCartDocument
                .orElseGet(() ->
                        getCartByUserIdFromDbOrCreateIt(userId));
    }


    private CartDocument getCartByUserIdFromDbOrCreateIt(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    User user = new User();
                    user.setId(userId);
                    newCart.setUser(user);
                    newCart.setItems(Collections.emptyList());
                    return newCart;
                });

        CartDocument cartDocument = cartDocumentConverter.toCartDocument(cart);
        return cartRedisDocumentRepository.save(cartDocument);
    }

}
