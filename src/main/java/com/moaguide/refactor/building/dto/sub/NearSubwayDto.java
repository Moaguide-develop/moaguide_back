package com.moaguide.refactor.building.dto.sub;


import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class NearSubwayDto {

	private String station;

	private String route;

	private Integer distance;

	private Integer time;


	public List<String> getRoutes() {
		// route가 null이 아니면 ','로 나누어 리스트로 반환
		if (this.route != null && !this.route.isEmpty()) {
			return Arrays.asList(this.route.split(","));
		}
		// route가 null이거나 비어 있으면 빈 리스트 반환
		return List.of();
	}
}