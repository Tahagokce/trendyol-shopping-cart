package com.trendyol.entity.db.seller;

import com.trendyol.common.enums.SellerType;
import com.trendyol.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seller extends BaseEntity {
    private SellerType type;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
