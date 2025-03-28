package com.moaguide.dto.NewDto;

import com.moaguide.dto.NewDto.BuildingDto.SubwayDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BuildingSubwayResponseDto {
    private List<SubwayDto> subwayDay;
    private List<SubwayDto> subwayMonth;

    public BuildingSubwayResponseDto(List<SubwayDto> subwayDay,List<SubwayDto> subwayMonth){
        this.subwayDay = subwayDay;
        this.subwayMonth = subwayMonth;
    }

    public BuildingSubwayResponseDto(){
        this.subwayDay = new ArrayList<>();
        this.subwayMonth = new ArrayList<>();
    }
}
