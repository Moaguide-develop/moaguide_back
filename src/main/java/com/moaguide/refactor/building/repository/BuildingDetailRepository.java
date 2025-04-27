package com.moaguide.refactor.building.repository;


import com.moaguide.refactor.building.entity.base.BuildingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingDetailRepository extends JpaRepository<BuildingDetail, Long> {

}

