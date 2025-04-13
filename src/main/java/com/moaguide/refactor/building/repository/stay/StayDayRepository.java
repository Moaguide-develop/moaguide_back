package com.moaguide.refactor.building.repository.stay;

import com.moaguide.refactor.building.dto.graph.StayDayDto;
import com.moaguide.refactor.building.entity.stayday.StayDay;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StayDayRepository extends JpaRepository<StayDay, Long> {

	@Query("SELECT new com.moaguide.refactor.building.dto.graph.StayDayDto(s.day,s.noday,s.oneday,s.twoday,s.threeday,s.total,s.nodayRate,s.onedayRate,s.twodayRate,s.threedayRate) FROM StayDay s where s.keyword =:keyword and YEAR(s.day) between :startyear and :endyear order by s.day ")
	List<StayDayDto> findByKeywordandyear(@Param("keyword") String keyword,
		@Param("startyear") int syear, @Param("endyear") int eyear);
}
