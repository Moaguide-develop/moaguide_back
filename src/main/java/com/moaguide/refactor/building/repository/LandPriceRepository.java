package com.moaguide.refactor.building.repository;

import com.moaguide.refactor.building.dto.graph.LandDto;
import com.moaguide.refactor.building.entity.LandPrice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LandPriceRepository extends JpaRepository<LandPrice, Long> {

	@Query("SELECT  new com.moaguide.refactor.building.dto.graph.LandDto(L.landPrice, L.baseYear) from LandPrice L WHERE L.productId.productId = :productId ORDER BY L.baseYear DESC, L.baseDay DESC")
	List<LandDto> findAllByproductId(@Param("productId") String Id);
}
