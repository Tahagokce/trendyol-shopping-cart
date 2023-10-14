package com.trendyol.common.model.request.shoppingcart;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddItemRequest {

    @NotNull(message = "Item cannot be empty")
    private Long itemId;

    @NotNull(message = "Category cannot be empty")
    private Long categoryId;

    @NotNull(message = "Seller cannot be empty")
    private Long sellerId;

    @NotNull
    @Min(value = 0, message = "Price must be at least 0")
    private Double price;

    @NotNull
    @Min(value = 1, message = "Quantity must be at least 0")
    @Max(value = 10, message = "Quantity maximum must 10")
    private Integer quantity;

}
