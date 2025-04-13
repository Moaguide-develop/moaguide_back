package com.moaguide.refactor.building.repository;


import com.moaguide.refactor.building.dto.sub.BusinessAreaDto;
import com.moaguide.refactor.building.entity.BusinessArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessAreaRepository extends JpaRepository<BusinessArea, Long> {

	@Query(
		"SELECT new com.moaguide.refactor.building.dto.sub.BusinessAreaDto( b.cbd, b.cbdDistance , b.cbdCar , b.cbdSubway, b.gbd , b.gbdDistance , b.gbdCar , b.gbdSubway, b.ybd, b.ybdDistance, b.ybdCar, b.ybdSubway ) "
			+
			"FROM BusinessArea b,BuildingDetail bd " +
			"WHERE bd.productId.productId =  b.productId.productId and " +
			"b.productId.productId = :id ")
	BusinessAreaDto findByproductId(@Param("id") String product_Id);
}
