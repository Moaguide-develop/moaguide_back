package com.moaguide.refactor.building.dto.sub;

import java.util.List;
import lombok.Getter;

@Getter

public class NearSubwayListDto {

	private final String station;

	private final List<String> route;

	private final Integer distance;

	private final Integer time;

	public NearSubwayListDto(NearSubwayDto nearSubway) {
		this.station = nearSubway.getStation();
		this.distance = nearSubway.getDistance();
		this.time = nearSubway.getTime();
		this.route = nearSubway.getRoutes();
	}

}
