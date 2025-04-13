package com.moaguide.refactor.building.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VacancyrateDto {

	private int year;
	private int quarter;
	private String region;
	private double value;
}
