package com.moaguide.refactor.building.repository;

import com.moaguide.refactor.building.dto.graph.VacancyrateDto;
import com.moaguide.refactor.building.entity.VacancyRate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRateRepository extends JpaRepository<VacancyRate, Long> {

	@Query("SELECT new com.moaguide.refactor.building.dto.graph.VacancyrateDto(v.year,v.quarter,v.region,v.vacancyRate) FROM VacancyRate v,BuildingDetail bd where v.keyword = bd.keyword AND v.type = :type AND bd.productId.productId = :productId AND v.year between :syear and :eyear order by v.region,v.year,v.quarter")
	List<VacancyrateDto> findByLastmonth(@Param("productId") String product_Id,
		@Param("type") String type, @Param("syear") int syear, @Param("eyear") int eyear);


	@Query("SELECT COUNT(DISTINCT v.region) FROM VacancyRate v,BuildingDetail bd where v.keyword = bd.keyword and bd.productId.productId = :productId")
	int findDistinctRegionCount(@Param("productId") String productId);
}
