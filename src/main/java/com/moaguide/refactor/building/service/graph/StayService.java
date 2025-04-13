package com.moaguide.refactor.building.service.graph;

import com.moaguide.refactor.building.dto.graph.StayDayDto;
import com.moaguide.refactor.building.dto.graph.StayRateDto;
import com.moaguide.refactor.building.repository.stay.StayDayRepository;
import com.moaguide.refactor.building.repository.stay.StayRateRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StayService {

	private final StayDayRepository stayDayRepository;
	private final StayRateRepository stayrateRepository;

	public List<StayDayDto> findbykeyword(String keyword, int syear, int eyear) {
		return stayDayRepository.findByKeywordandyear(keyword, syear, eyear);
	}

	public List<StayRateDto> findRateBykeyword(String keyword, int syear, int eyear) {
		return stayrateRepository.findByKeywordandyear(keyword, syear, eyear);
	}
}
