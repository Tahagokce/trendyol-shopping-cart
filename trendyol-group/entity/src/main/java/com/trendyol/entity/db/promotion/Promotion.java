package com.trendyol.entity.db.promotion;

import com.trendyol.common.enums.PromotionType;
import com.trendyol.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Promotion extends BaseEntity {

    private PromotionType type;

    private Double rate;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
