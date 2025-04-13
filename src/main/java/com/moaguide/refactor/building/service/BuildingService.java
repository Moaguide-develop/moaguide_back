package com.moaguide.refactor.building.service;

import com.moaguide.refactor.building.dto.base.BuildingReponseDto;
import com.moaguide.refactor.building.repository.BuildingDetailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Slf4j
@Service
public class BuildingService {

	private final BuildingDetailRepository buildingRepository;


	@Transactional(readOnly = false)
	public BuildingReponseDto findBydetail(String productId, String nickname) {
		BuildingReponseDto building = buildingRepository.findBuildingDetail(productId, nickname);
		return building;
	}

}
