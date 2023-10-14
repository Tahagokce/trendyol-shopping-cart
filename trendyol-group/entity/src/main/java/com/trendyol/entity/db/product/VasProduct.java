package com.trendyol.entity.db.product;

import com.trendyol.core.entity.BaseEntity;
import com.trendyol.entity.db.category.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VasProduct extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne
    private Product product;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
