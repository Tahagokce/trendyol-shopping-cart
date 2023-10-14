package com.trendyol.entity.db.product;

import com.trendyol.common.enums.ProductType;
import com.trendyol.core.entity.BaseEntity;
import com.trendyol.entity.db.category.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private ProductType type;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VasProduct> vasProducts;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
