package com.moaguide.refactor.building.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDetailDto {

	private String name;

	private String address;

	private String useArea;

	private String mainUse;

	private String completionDate;

	private String landArea;

	private String floorArea;

	private Double floorAreaRate;

	private Double dryRatio;

	private Double height;

	private String scale;

	private String mainStructure;

	private Integer parking;

	private Integer lift;


	// 필요한 필드를 받는 새로운 생성자 추가
	public BuildingDetailDto(String name, String useArea, String mainUse, String completionDate,
		String landArea, String floorArea,
		Double floorAreaRate, Double dryRatio, Double height, String scale, String mainStructure,
		Integer parking, Integer lift, String location) {
		this.name = name;
		this.useArea = useArea;
		this.mainUse = mainUse;
		this.completionDate = completionDate;
		this.landArea = landArea;
		this.floorArea = floorArea;
		this.floorAreaRate = floorAreaRate;
		this.dryRatio = dryRatio;
		this.height = height;
		this.scale = scale;
		this.mainStructure = mainStructure;
		this.parking = parking;
		this.lift = lift;
		this.address = location;
	}
}
