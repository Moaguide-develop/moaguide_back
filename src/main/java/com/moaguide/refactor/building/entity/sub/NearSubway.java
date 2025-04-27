package com.moaguide.refactor.building.entity.sub;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Near_Subway")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NearSubway {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String keyword;

	private String station;

	@Column(name = "Route")
	private String route;

	@Column(name = "Distance")
	private int distance;

	@Column(name = "Time")
	private int time;
}
