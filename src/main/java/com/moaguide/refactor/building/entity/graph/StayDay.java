package com.moaguide.refactor.building.entity.graph;


import jakarta.persistence.Column;
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
@Table(name = "Stay_Day")
public class StayDay {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String keyword;
	private Date day;
	private int noday;
	private int oneday;
	private int twoday;
	private int threeday;
	private int total;
	@Column(name = "noday_rate")
	private double nodayRate;
	@Column(name = "oneday_rate")
	private double onedayRate;
	@Column(name = "twoday_rate")
	private double twodayRate;
	@Column(name = "threeday_rate")
	private double threedayRate;
}
