package com.moaguide.refactor.building.repository;

import com.moaguide.refactor.building.entity.graph.Districts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictsRepository extends JpaRepository<Districts,Integer> {
}
