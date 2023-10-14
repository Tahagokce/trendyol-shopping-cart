package com.trendyol.shoppingcartservice.card.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import com.trendyol.entity.document.cart.CartDocument;

public interface CartRedisDocumentRepository extends RedisDocumentRepository<CartDocument, Long> { }
