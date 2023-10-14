package com.trendyol.common.model.input.cart;

import com.trendyol.core.model.input.BaseFileInputPayload;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddVasItemToItemInputPayload extends BaseFileInputPayload {

    private Long itemId;

    private Long vasItemId;

    private Long vasCategoryId;

    private Long vasSellerId;

    private Double price;

    private Integer quantity;

}
