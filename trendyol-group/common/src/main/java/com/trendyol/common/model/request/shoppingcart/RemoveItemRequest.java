package com.trendyol.common.model.request.shoppingcart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RemoveItemRequest {

    @NotNull(message = "Item cannot be empty")
    private Long itemId;

}
