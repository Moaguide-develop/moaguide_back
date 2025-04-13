package com.moaguide.refactor.building.service.sub;

import com.moaguide.refactor.building.dto.sub.NearBusDto;
import com.moaguide.refactor.building.repository.near.NearBusRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class NearBusService {

	private final NearBusRepository nearBusRepository;


	public List<NearBusDto> findNearBus(String productId) {
		return nearBusRepository.findBykeyword(productId);
	}
}
