package com.moaguide.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class searchProductDto {
    private String productId;
    private String name;
    private String platform;
    private String category;
}
