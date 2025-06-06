package com.moaguide.domain.hanwoo;

import com.moaguide.dto.NewDto.customDto.ProductionCostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionCostRepository extends JpaRepository<ProductionCost, Long> {
    @Query("SELECT new com.moaguide.dto.NewDto.customDto.ProductionCostDto(p.prdDe, p.prdDt) " +
            "FROM ProductionCost p " +
            "WHERE YEAR(p.prdDe) >= :year " +
            "GROUP BY YEAR(p.prdDe)")
    List<ProductionCostDto> findProductionCost(@Param("year") Integer year);
}
