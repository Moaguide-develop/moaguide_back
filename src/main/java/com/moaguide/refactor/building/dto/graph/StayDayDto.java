package com.moaguide.refactor.building.dto.graph;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StayDayDto {

	private Date day;
	private int noday;
	private int oneday;
	private int twoday;
	private int threeday;
	private int total;
	private double nodayRate;
	private double onedayRate;
	private double twodayRate;
	private double threedayRate;
}
