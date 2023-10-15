package com.trendyol.entity.document.cart;

import com.redis.om.spring.annotations.Document;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VasItemDocument document = (VasItemDocument) o;
        return Objects.equals(vasItemId, document.vasItemId) && Objects.equals(itemId, document.itemId) && Objects.equals(categoryId, document.categoryId) && Objects.equals(sellerId, document.sellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vasItemId, itemId, categoryId, sellerId);
    }
}
