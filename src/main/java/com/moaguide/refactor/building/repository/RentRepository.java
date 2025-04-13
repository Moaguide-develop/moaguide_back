package com.moaguide.refactor.building.repository;


import com.moaguide.refactor.building.dto.graph.TypeDto;
import com.moaguide.refactor.building.dto.graph.RentDto;
import com.moaguide.refactor.building.entity.Rent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

	@Query("SELECT new com.moaguide.refactor.building.dto.graph.RentDto(r.year,r.quarter,r.region,r.rent) FROM Rent r,BuildingDetail bd where r.keyword = bd.keyword  AND r.type = :type AND bd.productId.productId = :productId AND r.year between :syear and :eyear order by r.region,r.year,r.quarter")
	List<RentDto> findBytype(@Param("productId") String product_Id, @Param("type") String type,
		@Param("syear") int syear, @Param("eyear") int eyear);

	@Query("SELECT distinct new com.moaguide.refactor.building.dto.graph.TypeDto( r.type) FROM BuildingDetail bd,Rent r where bd.keyword = r.keyword and bd.productId.productId=:productId")
	List<TypeDto> findType(@Param("productId") String product_Id);

	@Query("SELECT COUNT(DISTINCT r.region) FROM Rent r,BuildingDetail bd where r.keyword = bd.keyword and bd.productId.productId = :productId")
	int findDistinctRegionCount(@Param("productId") String product_Id);
}
