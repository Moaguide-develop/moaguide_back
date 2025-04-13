package com.moaguide.refactor.building.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubwayWeekDto {

	private int year;
	private int month;
	private String weekDay;
	private int totalBoarding;
	private int totalAlighting;
}
