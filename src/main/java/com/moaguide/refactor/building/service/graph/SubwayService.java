package com.moaguide.refactor.building.service.graph;

import com.moaguide.refactor.building.dto.graph.BuildingSubwayResponseDto;
import com.moaguide.refactor.building.dto.graph.SubwayDto;
import com.moaguide.refactor.building.repository.subway.SubwayDayRepository;
import com.moaguide.refactor.building.repository.subway.SubwayMonthRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class SubwayService {

	private final SubwayDayRepository subwayDayRepository;
	private final SubwayMonthRepository subwayMonthRepository;

	@Transactional
	public BuildingSubwayResponseDto findByProductId(String productId) {
		LocalDate minust1Month = LocalDate.now().minusMonths(1);
		Date date = Date.valueOf(minust1Month);
		List<SubwayDto> SubwayDay = subwayDayRepository.callSubwayDayProcedure(productId, date);
		List<SubwayDto> SubwayMonth = subwayMonthRepository.callSubwayMonthProcedure(productId);
		return new BuildingSubwayResponseDto(SubwayDay, SubwayMonth);
	}
}
