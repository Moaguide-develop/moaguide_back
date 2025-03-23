package com.moaguide.dto.NewDto;

import com.moaguide.dto.NewDto.BuildingDto.SubwayDto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BuildingSubwayResponseDto {
    private final List<SubwayDto> subwayDay;
    private final List<SubwayDto> subwayMonth;

    public BuildingSubwayResponseDto(List<SubwayDto> subwayDay,List<SubwayDto> subwayMonth){
        this.subwayDay = subwayDay;
        this.subwayMonth = subwayMonth;
    }

    public BuildingSubwayResponseDto(){
        this.subwayDay = new ArrayList<>();
        this.subwayMonth = new ArrayList<>();
    }
}
