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
public class CartItemResource extends BaseResource {
    private Long itemId;
    private Long categoryId;
    private Long sellerId;
    private Double price;
    private Integer quantity;
    private List<VasItemResource> vasItems = new ArrayList<>();
}
