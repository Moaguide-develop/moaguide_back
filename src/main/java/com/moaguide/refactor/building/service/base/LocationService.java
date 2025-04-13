package com.moaguide.refactor.building.service.base;


import com.moaguide.refactor.building.dto.base.LocationDto;
import com.moaguide.refactor.building.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationService {

	private LocationRepository locationRepository;

	public LocationDto locate(String id) {
		return locationRepository.findByProductId(id);
	}
}
