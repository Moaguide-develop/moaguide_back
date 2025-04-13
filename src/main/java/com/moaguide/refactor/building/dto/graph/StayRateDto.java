package com.moaguide.refactor.building.dto.graph;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StayRateDto {

	private Date day;
	private Double rate;
	private Double value;
}

