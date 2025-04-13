package com.moaguide.refactor.product.repository;

import com.moaguide.refactor.building.dto.base.BuildingReponseDto;
import com.moaguide.refactor.product.dto.SummaryIssupriceCustomDto;
import com.moaguide.refactor.product.entity.Product;
import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	@Query("SELECT CEIL(count(p)/10) FROM Product p JOIN Platform pl ON pl.PlatformId = p.PlatformId.PlatformId WHERE pl.status = :status")
	int findlistTotal(@Param("status") String status);

	@Query("SELECT CEIL(count(p)/10) FROM Product p JOIN Platform pl ON pl.PlatformId = p.PlatformId.PlatformId WHERE pl.status = :status and pl.category = :category")
	int findlistTotalCategory(@Param("status") String status, @Param("category") String category);

	@Query("SELECT CEIL(count(p)/10) FROM Product p JOIN Platform pl ON pl.PlatformId = p.PlatformId.PlatformId JOIN Bookmark b ON b.productId.productId=p.productId WHERE pl.status = :status AND b.nickName.nickname = :nickname")
	int findlistTotalByBookmark(@Param("status") String status, @Param("nickname") String nickname);

	@Modifying
	@Query("update Product p set p.views = p.views + 1 where p.productId = :productId")
	void updateByProductId(@Param("productId") String productId);

	@Query(
		"SELECT new com.moaguide.refactor.product.dto.SummaryIssupriceCustomDto(p.productId, pl.category, p.name, (p.nowPiece / p.piece * 100),false) "
			+
			"FROM Product p JOIN Platform pl on pl.PlatformId = p.PlatformId.PlatformId JOIN IssuePrice ip on ip.id.productId.productId = p.productId "
			+
			"WHERE pl.PlatformId = p.PlatformId.PlatformId " +
			"AND pl.status = '공모중' " +
			"AND ip.id.productId.productId = p.productId " +
			"AND ip.day <= :date " +
			"ORDER BY ip.day DESC")
	List<SummaryIssupriceCustomDto> findrecent(Pageable pageable, @Param("date") Date date);

	@Procedure(name = "building_detail")
	BuildingReponseDto findBuildingDetail(@Param("in_Product_Id") String productId,
		@Param("nickname") String nickname);
}

