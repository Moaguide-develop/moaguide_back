package com.moaguide.dto.NewDto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SummaryResponseDto {
    private List<?> product;
    private int page;
    private int size;
    private int totalPages;

    public SummaryResponseDto(List<?> product, int page, int size,int total) {
        this.product = product;
        this.page = page+1;
        this.size = size;
        this.totalPages  = total;
    }
}
