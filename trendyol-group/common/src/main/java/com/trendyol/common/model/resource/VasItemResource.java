package com.trendyol.common.model.resource;

import com.trendyol.core.model.resource.BaseResource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VasItemResource extends BaseResource {
    private Long vasItemId;
    private Long vasCategoryId;
    private Long vasSellerId;
    private Double price;
    private Integer quantity;
}
