package com.trendyol.core.model.dto;

import com.trendyol.core.model.output.BaseFileOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
public class ShoppingCartFileOutputDto {
    private String fileName;
    private BaseFileOutput output;
}
