package com.moaguide.refactor.building.service.base;

import com.moaguide.refactor.building.dto.base.LeaseDto;
import com.moaguide.refactor.building.repository.LeaseRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaseService {

	private final LeaseRepository leaseRepository;

	public List<LeaseDto> detail(String id) {
		List<LeaseDto> lease = leaseRepository.findByproductId(id);
		return lease;
	}
}
