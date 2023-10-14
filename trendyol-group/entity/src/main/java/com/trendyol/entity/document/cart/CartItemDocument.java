package com.trendyol.entity.document.cart;

import com.redis.om.spring.annotations.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class CartItemDocument {

    private Long itemId;

    private Long categoryId;

    private Long sellerId;

    private Double price;

    private Integer quantity;

    private List<VasItemDocument> vasItems = new ArrayList<>();

}
