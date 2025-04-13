package com.moaguide.refactor.building.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RentDto {

	private int year;
	private int quarter;
	private String region;
	private double value;
}
