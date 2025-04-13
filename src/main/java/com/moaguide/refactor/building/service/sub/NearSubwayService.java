package com.moaguide.refactor.building.service.sub;

import com.moaguide.refactor.building.dto.sub.NearSubwayDto;
import com.moaguide.refactor.building.repository.near.NearSubwayRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class NearSubwayService {

	private final NearSubwayRepository nearSubwayRepository;

	@Transactional
	public List<NearSubwayDto> findBykeyword(String productId) {
		List<NearSubwayDto> nearSubway = nearSubwayRepository.findBykeyword(productId);
		return nearSubway;
	}
}
