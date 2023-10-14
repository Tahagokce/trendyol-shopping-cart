package com.trendyol.entity.document.cart;

import com.redis.om.spring.annotations.Document;
import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class VasItemDocument {

    private Long vasItemId;

    private Long itemId;

    private Long categoryId;

    private Long sellerId;

    private Double price;

    private Integer quantity;
}
