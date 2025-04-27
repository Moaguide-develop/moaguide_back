package com.moaguide.refactor.building.repository.graph.subway;

import com.moaguide.refactor.building.dto.graph.SubwayDto;
import com.moaguide.refactor.building.entity.graph.SubwayDay;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubwayDayRepository extends JpaRepository<SubwayDay, Long> {

	@Procedure(name = "SubwayDay")
	List<SubwayDto> callSubwayDayProcedure(@Param("productId") String productId,
		@Param("date") Date date);
}
