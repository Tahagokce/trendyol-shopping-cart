package com.trendyol.entity.db.cart;

import com.trendyol.core.entity.BaseEntity;
import com.trendyol.entity.db.category.Category;
import com.trendyol.entity.db.product.Product;
import com.trendyol.entity.db.seller.Seller;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Double price;

    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
