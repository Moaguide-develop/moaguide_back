package com.moaguide.refactor.building.service.graph;

import com.moaguide.refactor.building.dto.graph.RentDto;
import com.moaguide.refactor.building.dto.graph.TypeDto;
import com.moaguide.refactor.building.repository.RentRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class RentService {

	private RentRepository rentRepository;


	public Map<String, List<RentDto>> getRentByRegion(String product_Id, String type, int syear,
		int eyear) {
		// 첫 번째 쿼리: 고유한 지역 개수 가져오기
		int regionCount = rentRepository.findDistinctRegionCount(product_Id);

		// 두 번째 쿼리: 전체 데이터 가져오기
		List<RentDto> rentList = rentRepository.findBytype(product_Id, type, syear, eyear);

		// 만약 regionCount가 0이거나 rentList가 비어있다면 null 반환
		if (regionCount == 0 || rentList.isEmpty()) {
			return new HashMap<>();
		}

		// 전체 데이터를 지역별로 나눌 수 있는 크기 계산
		int sizePerRegion = rentList.size() / regionCount;

		Map<String, List<RentDto>> rentMap = new HashMap<>();
		for (int i = 0; i < regionCount; i++) {
			// 각 region에 할당된 데이터를 인덱스 기반으로 나눔
			int startIndex = i * sizePerRegion;
			int endIndex = (i == regionCount - 1) ? rentList.size() : (i + 1) * sizePerRegion;

			List<RentDto> regionData = rentList.subList(startIndex, endIndex);
			String regionName = regionData.get(0).getRegion(); // 첫 번째 데이터의 지역 이름을 기준으로 사용

			rentMap.put(regionName, regionData);
		}
		return rentMap;
	}

	@Transactional(readOnly = false)
	public List<TypeDto> findType(String productId) {
		List<TypeDto> rents = rentRepository.findType(productId);
		return rents;
	}
}
