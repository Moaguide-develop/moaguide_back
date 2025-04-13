package com.moaguide.refactor.building.service.graph;

import com.moaguide.refactor.building.dto.graph.LandDto;
import com.moaguide.refactor.building.repository.LandPriceRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LandPriceService {

	private LandPriceRepository landPriceRepository;

	public List<LandDto> priceList(String id) {
		List<LandDto> list = landPriceRepository.findAllByproductId(id);
		return list;
	}
}
