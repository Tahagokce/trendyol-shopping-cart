package com.trendyol.entity.document.cart;

import com.redis.om.spring.annotations.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemDocument that = (CartItemDocument) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(categoryId, that.categoryId) && Objects.equals(sellerId, that.sellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, categoryId, sellerId);
    }
}
