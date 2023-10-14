package com.trendyol.entity.document.cart;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class CartDocument {

    @Id
    private Long id;

    private Long cartId;

    @NonNull
    @Indexed
    private Long userId;

    private List<CartItemDocument> items = new ArrayList<>();

    private Double totalAmount;

    private Double totalDiscount;

    private Long appliedPromotionId;
}
