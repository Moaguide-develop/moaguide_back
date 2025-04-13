package com.moaguide.refactor.building.service.graph;

import com.moaguide.refactor.building.dto.graph.VacancyrateDto;
import com.moaguide.refactor.building.repository.VacancyRateRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VacancyRateService {

	private VacancyRateRepository vacancyRateRepository;


	public Map<String, List<VacancyrateDto>> findBase(String product_Id, String type, int syear,
		int eyear) {
		int regionCount = vacancyRateRepository.findDistinctRegionCount(product_Id);
		List<VacancyrateDto> vacancyList = vacancyRateRepository.findByLastmonth(product_Id, type,
			syear, eyear);
		// 만약 regionCount가 0이거나 vacancyList가 비어있다면 null 반환
		if (regionCount == 0 || vacancyList.isEmpty()) {
			return new HashMap<>();
		}
		// 전체 데이터를 지역별로 나눌 수 있는 크기 계산
		int sizePerRegion = vacancyList.size() / regionCount;
		Map<String, List<VacancyrateDto>> rentMap = new HashMap<>();
		for (int i = 0; i < regionCount; i++) {
			// 각 region에 할당된 데이터를 인덱스 기반으로 나눔
			int startIndex = i * sizePerRegion;
			int endIndex = (i == regionCount - 1) ? vacancyList.size() : (i + 1) * sizePerRegion;

			List<VacancyrateDto> regionData = vacancyList.subList(startIndex, endIndex);
			String regionName = regionData.get(0).getRegion(); // 첫 번째 데이터의 지역 이름을 기준으로 사용

			rentMap.put(regionName, regionData);
		}
		return rentMap;
	}
}
