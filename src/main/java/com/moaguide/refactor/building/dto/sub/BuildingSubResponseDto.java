package com.moaguide.refactor.building.dto.sub;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuildingSubResponseDto {

	private String cbd;
	private String cbdDistance;
	private String cbdCar;
	private String cbdSubway;
	private String gbd;
	private String gbdDistance;
	private String gbdCar;
	private String gbdSubway;
	private String ybd;
	private String ybdDistance;
	private String ybdCar;
	private String ybdSubway;
	private List<NearSubwayListDto> nearSubway;
	private List<NearBusDto> nearBus;


	public BuildingSubResponseDto(BusinessAreaDto businessArea, List<NearSubwayDto> nearSubway,
		List<NearBusDto> bus) {
		if (businessArea != null) {
			this.cbd = businessArea.getCbd();
			this.cbdDistance = businessArea.getCbdDistance();
			this.cbdCar = businessArea.getCbdCar();
			this.cbdSubway = businessArea.getCbdSubway();
			this.gbd = businessArea.getGbd();
			this.gbdDistance = businessArea.getGbdDistance();
			this.gbdCar = businessArea.getGbdCar();
			this.gbdSubway = businessArea.getGbdSubway();
			this.ybd = businessArea.getYbd();
			this.ybdDistance = businessArea.getYbdDistance();
			this.ybdCar = businessArea.getYbdCar();
			this.ybdSubway = businessArea.getYbdSubway();
		} else {
			this.cbd = "";
			this.cbdDistance = "";
			this.cbdCar = "";
			this.cbdSubway = "";
			this.gbd = "";
			this.gbdDistance = "";
			this.gbdCar = "";
			this.gbdSubway = "";
			this.ybd = "";
			this.ybdDistance = "";
			this.ybdCar = "";
			this.ybdSubway = "";
		}

		this.nearSubway = new ArrayList<>();
		if (nearSubway != null) {
			// nearSubway 리스트에 NearSubwayListDto 객체를 추가
			for (NearSubwayDto near : nearSubway) {
				this.nearSubway.add(new NearSubwayListDto(near));
			}
		}

		if (bus != null) {
			this.nearBus = bus;
		} else {
			this.nearBus = new ArrayList<>();
		}
	}

}
