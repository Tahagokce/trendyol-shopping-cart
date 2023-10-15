package com.trendyol.common.model.request.shoppingcart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddVasItemToItemRequest {

    @NotNull(message = "Item cannot be empty")
    private Long itemId;

    @NotNull(message = "Vas Item cannot be empty")
    private Long vasItemId;

    @NotNull(message = "Vas Category cannot be empty")
    private Long vasCategoryId;

    @NotNull(message = "Vas Seller cannot be empty")
    private Long vasSellerId;

    @NotNull
    @Min(value = 0, message = "Price must be at least 0")
    private Double price;

    @NotNull
    @Min(value = 1, message = "Quantity must be at least 0")
    private Integer quantity;

}
