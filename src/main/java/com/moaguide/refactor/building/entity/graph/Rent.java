package com.moaguide.refactor.building.entity.graph;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rent")

public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String keyword;
	private String region;
	private int year;
	private int quarter;
	private double rent;
	private String type;
}
