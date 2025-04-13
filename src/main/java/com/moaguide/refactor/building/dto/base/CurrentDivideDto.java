package com.moaguide.refactor.building.dto.base;


import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentDivideDto {

	private Double lastDivide;
	private Integer divideCycle;
	private Date paymentDay;
	private Double divideRate;

}
