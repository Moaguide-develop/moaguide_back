package com.moaguide.refactor.building.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class PopulationDto {

	private String weekDay;

	private Integer total;

	private Integer age0;

	private Integer age10;

	private Integer age20;

	private Integer age30;

	private Integer age40;

	private Integer age50;

	private Integer age60;

	private Integer age70;

	private Integer man;

	private Integer girl;
}
