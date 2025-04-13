package com.moaguide.refactor.building.repository;

import com.moaguide.refactor.building.dto.graph.PopulationDto;
import com.moaguide.refactor.building.entity.Population;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PopulationRepository extends JpaRepository<Population, Long> {

	@Query(
		"select new com.moaguide.refactor.building.dto.graph.PopulationDto(p.weekDay ,p.total ,p.age0 ,p.age10 ,p.age20,p.age30,p.age40,p.age50,p.age60,p.age70,p.man,p.girl )"
			+
			"FROM Population p,BuildingDetail b " +
			"where p.districts.districtsId = b.districtsId.districtsId and b.productId.productId = :id "
			+
			"ORDER BY p.day desc, CASE " +
			"WHEN p.weekDay = 'Monday' THEN 1 " +
			"WHEN p.weekDay = 'Tuesday' THEN 2 " +
			"WHEN p.weekDay = 'Wednesday' THEN 3 " +
			"WHEN p.weekDay = 'Thursday' THEN 4 " +
			"WHEN p.weekDay = 'Friday' THEN 5 " +
			"WHEN p.weekDay = 'Saturday' THEN 6 " +
			"WHEN p.weekDay = 'Sunday' THEN 7 " +
			"END")
	List<PopulationDto> findByLastmonth(@Param("id") String ProductId, Pageable page);
}
