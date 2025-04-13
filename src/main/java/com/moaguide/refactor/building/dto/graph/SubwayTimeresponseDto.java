package com.moaguide.refactor.building.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class SubwayTimeresponseDto {

	private int year;
	private int month;
	private List<Integer> boarding;
	private List<Integer> alighting;

	public SubwayTimeresponseDto(SubwayTimeDto subwayTimeDto) {
		this.year = subwayTimeDto.getYear();
		this.month = subwayTimeDto.getMonth();
		this.boarding = new ArrayList<>(19); // 미리 19개의 공간을 예약
		this.alighting = new ArrayList<>(19);
		this.boarding.add(subwayTimeDto.getBoarding05());
		this.boarding.add(subwayTimeDto.getBoarding06());
		this.boarding.add(subwayTimeDto.getBoarding07());
		this.boarding.add(subwayTimeDto.getBoarding08());
		this.boarding.add(subwayTimeDto.getBoarding09());
		this.boarding.add(subwayTimeDto.getBoarding10());
		this.boarding.add(subwayTimeDto.getBoarding11());
		this.boarding.add(subwayTimeDto.getBoarding12());
		this.boarding.add(subwayTimeDto.getBoarding13());
		this.boarding.add(subwayTimeDto.getBoarding14());
		this.boarding.add(subwayTimeDto.getBoarding15());
		this.boarding.add(subwayTimeDto.getBoarding16());
		this.boarding.add(subwayTimeDto.getBoarding17());
		this.boarding.add(subwayTimeDto.getBoarding18());
		this.boarding.add(subwayTimeDto.getBoarding19());
		this.boarding.add(subwayTimeDto.getBoarding20());
		this.boarding.add(subwayTimeDto.getBoarding21());
		this.boarding.add(subwayTimeDto.getBoarding22());
		this.boarding.add(subwayTimeDto.getBoarding23());
		this.alighting.add(subwayTimeDto.getAlighting05());
		this.alighting.add(subwayTimeDto.getAlighting06());
		this.alighting.add(subwayTimeDto.getAlighting07());
		this.alighting.add(subwayTimeDto.getAlighting08());
		this.alighting.add(subwayTimeDto.getAlighting09());
		this.alighting.add(subwayTimeDto.getAlighting10());
		this.alighting.add(subwayTimeDto.getAlighting11());
		this.alighting.add(subwayTimeDto.getAlighting12());
		this.alighting.add(subwayTimeDto.getAlighting13());
		this.alighting.add(subwayTimeDto.getAlighting14());
		this.alighting.add(subwayTimeDto.getAlighting15());
		this.alighting.add(subwayTimeDto.getAlighting16());
		this.alighting.add(subwayTimeDto.getAlighting17());
		this.alighting.add(subwayTimeDto.getAlighting18());
		this.alighting.add(subwayTimeDto.getAlighting19());
		this.alighting.add(subwayTimeDto.getAlighting20());
		this.alighting.add(subwayTimeDto.getAlighting21());
		this.alighting.add(subwayTimeDto.getAlighting22());
		this.alighting.add(subwayTimeDto.getAlighting23());
	}
}
