package com.moaguide.refactor.building.repository.subway;

import com.moaguide.refactor.building.dto.graph.SubwayDto;
import com.moaguide.refactor.building.entity.subway.SubwayMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubwayMonthRepository extends JpaRepository<SubwayMonth, Long> {


	@Procedure(name = "SubwayMonth")
	List<SubwayDto> callSubwayMonthProcedure(@Param("productId") String productId);
}
