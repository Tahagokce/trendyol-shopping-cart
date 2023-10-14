package com.trendyol.common.model.input.cart;

import com.trendyol.core.model.input.BaseFileInputPayload;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RemoveItemInputPayload extends BaseFileInputPayload {
    private Long itemId;
}
