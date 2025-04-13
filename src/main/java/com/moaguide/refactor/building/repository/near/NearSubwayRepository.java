package com.moaguide.refactor.building.repository.near;

import com.moaguide.refactor.building.dto.sub.NearSubwayDto;
import com.moaguide.refactor.building.entity.near.NearSubway;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface NearSubwayRepository extends JpaRepository<NearSubway, Long> {

	@Query(
		"SELECT new com.moaguide.refactor.building.dto.sub.NearSubwayDto(ns.station ,ns.route,ns.distance,ns.time) "
			+
			"FROM BuildingDetail bd ,NearSubway ns " +
			"where bd.keyword = ns.keyword " +
			"and bd.productId.productId = :id")
	List<NearSubwayDto> findBykeyword(@Param("id") String ProductId);
}
