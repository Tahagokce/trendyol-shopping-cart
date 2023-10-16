package com.trendyol.shoppingcartservice.card.service.impl;

import com.trendyol.entity.db.cart.Cart;
import com.trendyol.entity.document.cart.CartDocument;
import com.trendyol.shoppingcartservice.card.converter.CartDocumentConverter;
import com.trendyol.shoppingcartservice.card.repository.CartRedisDocumentRepository;
import com.trendyol.shoppingcartservice.card.repository.CartRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartRedisDocumentRepository cartRedisDocumentRepository;

    @Mock
    private CartDocumentConverter cartDocumentConverter;

    @Test
    void testSave() {
        // arrange
        CartDocument cart = new EasyRandom().nextObject(CartDocument.class);

        // act
        cartService.save(cart);

        // assert
        Mockito.verify(cartRedisDocumentRepository).save(any());
    }

    @Test
    void testFindByUserId_whenGetInRedis() {
        // arrange
        CartDocument cart = new EasyRandom().nextObject(CartDocument.class);
        Mockito.when(cartRedisDocumentRepository.findById(any())).thenReturn(Optional.of(cart));

        // act
        CartDocument actual = cartService.findByUserId(1L);

        // assert
        assertNotNull(actual);
    }

    @Test
    void testFindByUserId_whenIsNullIfItIsNotInTheRedisFetchItFromTheDatabase() {
        // arrange
        CartDocument cartDocument = new EasyRandom().nextObject(CartDocument.class);

        Mockito.when(cartRedisDocumentRepository.findById(any())).thenReturn(Optional.empty());

        Cart cart = new EasyRandom().nextObject(Cart.class);
        Mockito.when(cartRepository.findByUserId(any())).thenReturn(Optional.of(cart));

        Mockito.when(cartRedisDocumentRepository.save(any())).thenReturn(cartDocument);

        // act
        CartDocument actual = cartService.findByUserId(1L);

        // assert
        assertNotNull(actual);
    }

    @Test
    void testFindByUserId_whenIsNullIfItIsNotInTheRedisFetchItFromTheDatabaseIfItIsNotinDatabaseCreateANewOne() {
        // arrange
        CartDocument cart = new EasyRandom().nextObject(CartDocument.class);

        Mockito.when(cartRedisDocumentRepository.findById(any())).thenReturn(Optional.empty());
        Mockito.when(cartRepository.findByUserId(any())).thenReturn(Optional.empty());

        Mockito.when(cartRedisDocumentRepository.save(any())).thenReturn(cart);

        // act
        CartDocument actual = cartService.findByUserId(1L);

        // assert
        assertNotNull(actual);
    }
}
