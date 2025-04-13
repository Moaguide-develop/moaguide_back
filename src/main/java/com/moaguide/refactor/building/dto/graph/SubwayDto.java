package com.moaguide.refactor.building.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SubwayDto {

	private Date day;

	private int boarding;

	private int alighting;
}
