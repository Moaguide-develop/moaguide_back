package com.moaguide.refactor.building.service.sub;

import com.moaguide.refactor.building.dto.sub.BusinessAreaDto;
import com.moaguide.refactor.building.repository.BusinessAreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BusinessAreaService {

	private BusinessAreaRepository businessAreaRepository;


	public BusinessAreaDto findBase(String product_Id) {
		BusinessAreaDto businessArea = businessAreaRepository.findByproductId(product_Id);
		return businessArea;
	}
}
