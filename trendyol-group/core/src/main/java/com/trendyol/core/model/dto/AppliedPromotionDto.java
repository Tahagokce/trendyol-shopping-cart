package com.trendyol.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
public class AppliedPromotionDto {
    private Long appliedPromotionId;
    private Double totalAmount;
    private Double totalDiscount;
}
