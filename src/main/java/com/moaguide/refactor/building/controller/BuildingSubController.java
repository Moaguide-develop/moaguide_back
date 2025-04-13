package com.moaguide.refactor.building.controller;

import com.moaguide.refactor.building.dto.sub.BuildingSubResponseDto;
import com.moaguide.refactor.building.dto.sub.BusinessAreaDto;
import com.moaguide.refactor.building.dto.sub.NearBusDto;
import com.moaguide.refactor.building.dto.sub.NearSubwayDto;
import com.moaguide.refactor.building.service.sub.BusinessAreaService;
import com.moaguide.refactor.building.service.sub.NearBusService;
import com.moaguide.refactor.building.service.sub.NearSubwayService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 건물 상세정보 제공 컨트롤러
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/detail/building")
public class BuildingSubController {

	private final BusinessAreaService businessAreaService;
	private final NearBusService nearBusService;
	private final NearSubwayService nearSubwayService;

	/**
	 * 접근성 정보, 근처 지하철/버스 정보를 종합적으로 제공
	 *
	 * @param product_Id 콘텐츠 고유 ID
	 * @return 건물 주변 상세 정보 DTO
	 */
	@GetMapping("/sub/{product_Id}")
	public ResponseEntity<Object> add(@PathVariable String product_Id) {
		BusinessAreaDto businessArea = businessAreaService.findBase(product_Id);
		List<NearBusDto> bus = nearBusService.findNearBus(product_Id);
		List<NearSubwayDto> nearSubway = nearSubwayService.findBykeyword(product_Id);
		BuildingSubResponseDto buildingSubResponseDto = new BuildingSubResponseDto(businessArea,
			nearSubway, bus);
		return ResponseEntity.ok(buildingSubResponseDto);
	}
}
