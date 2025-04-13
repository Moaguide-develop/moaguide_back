package com.moaguide.refactor.building.repository;

import com.moaguide.refactor.building.dto.base.LocationDto;
import com.moaguide.refactor.building.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query(
		"SELECT new com.moaguide.refactor.building.dto.base.LocationDto( p.name ,l.longitude,l.latitude,bd.location ) "
			+
			"FROM Product p,Location l,BuildingDetail bd " +
			"where p.productId = l.productId.productId " +
			"AND p.productId = bd.productId.productId " +
			"and p.productId = :id ")
	LocationDto findByProductId(@Param("id") String productId);
}
