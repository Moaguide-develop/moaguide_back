package com.moaguide.refactor.building.repository.stay;

import com.moaguide.refactor.building.dto.graph.StayRateDto;
import com.moaguide.refactor.building.entity.stayday.StayRate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StayRateRepository extends JpaRepository<StayRate, Integer> {

	@Query("SELECT new com.moaguide.refactor.building.dto.graph.StayRateDto(s.day,s.rate,s.value) FROM StayRate s where s.keyword =:keyword and YEAR(s.day) between :startyear and :endyear order by s.day")
	List<StayRateDto> findByKeywordandyear(@Param("keyword") String keyword,
		@Param("startyear") int syear, @Param("endyear") int eyear);
}
