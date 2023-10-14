package com.trendyol.common.model.resource;

import com.trendyol.core.model.resource.BaseResource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CartResource extends BaseResource {
    private Double totalAmount;
    private Long appliedPromotionId;
    private Double totalDiscount;
    private List<CartItemResource> items = new ArrayList<>();
}
