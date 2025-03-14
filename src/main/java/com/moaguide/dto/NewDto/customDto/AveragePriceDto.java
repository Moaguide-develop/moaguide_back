package com.moaguide.dto.NewDto.customDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AveragePriceDto {
    private String day;
    private Double value;

    public AveragePriceDto(LocalDate preDe, Double averagePrice) {
        this.day = startDate(preDe);
        this.value = averagePrice;
    }

    // 연도와 월을 YYYY.MM 형식으로 변환하는 메서드
    private String startDate(LocalDate preDe) {
        return String.format("%d.%02d", preDe.getYear(), preDe.getMonthValue());
    }
}
