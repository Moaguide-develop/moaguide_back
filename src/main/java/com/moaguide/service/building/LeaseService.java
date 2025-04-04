package com.moaguide.service.building;

import com.moaguide.domain.building.lease.LeaseRepository;
import com.moaguide.dto.NewDto.BuildingDto.LeaseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LeaseService {
    private final LeaseRepository leaseRepository;

    public List<LeaseDto> detail(String id) {
        List<LeaseDto> lease = leaseRepository.findByproductId(id);
        return lease;
    }
}
