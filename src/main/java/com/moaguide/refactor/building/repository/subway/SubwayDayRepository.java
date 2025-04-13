package com.moaguide.refactor.building.repository.subway;

import com.moaguide.refactor.building.dto.graph.SubwayDto;
import com.moaguide.refactor.building.entity.subway.SubwayDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SubwayDayRepository extends JpaRepository<SubwayDay, Long> {

	@Procedure(name = "SubwayDay")
	List<SubwayDto> callSubwayDayProcedure(@Param("productId") String productId,
		@Param("date") Date date);
}
