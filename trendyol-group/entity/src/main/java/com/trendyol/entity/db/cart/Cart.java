package com.trendyol.entity.db.cart;

import com.trendyol.core.entity.BaseEntity;
import com.trendyol.entity.db.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart extends BaseEntity {

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
