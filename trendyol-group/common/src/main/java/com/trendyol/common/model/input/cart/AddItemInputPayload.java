package com.trendyol.common.model.input.cart;

import com.trendyol.core.model.input.BaseFileInputPayload;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddItemInputPayload extends BaseFileInputPayload {

    private Long itemId;

    private Long categoryId;

    private Long sellerId;

    private Double price;

    private Integer quantity;
}
