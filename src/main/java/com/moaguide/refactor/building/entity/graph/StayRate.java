package com.moaguide.refactor.building.entity.graph;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Stay_Rate")
public class StayRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String keyword;
	private Date day;
	private Double rate;
	private Double value;
}
