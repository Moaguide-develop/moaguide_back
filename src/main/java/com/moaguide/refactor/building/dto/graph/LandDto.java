package com.moaguide.refactor.building.dto.graph;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LandDto {

	private int value;
	private String day;

	public LandDto(int value, String day) {
		this.value = value;
		this.day = day;
	}
}
