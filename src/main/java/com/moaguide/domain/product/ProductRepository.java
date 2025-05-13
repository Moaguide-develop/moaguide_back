package com.moaguide.domain.product;

import com.moaguide.dto.NewDto.customDto.SummaryIssupriceCustomDto;
import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {


    @Query("SELECT CEIL(count(p)/10) FROM Product p JOIN Platform pl ON pl.PlatformId = p.PlatformId.PlatformId WHERE pl.status = :status")
    int findlistTotal(@Param("status") String status);

    @Query("SELECT CEIL(count(p)/10) FROM Product p JOIN Platform pl ON pl.PlatformId = p.PlatformId.PlatformId WHERE pl.status = :status and pl.category = :category")
    int findlistTotalCategory(@Param("status") String status,@Param("category") String category);

    @Query("SELECT CEIL(count(p)/10) FROM Product p JOIN Platform pl ON pl.PlatformId = p.PlatformId.PlatformId JOIN Bookmark b ON b.productId.productId=p.productId WHERE pl.status = :status AND b.nickName.nickname = :nickname")
    int findlistTotalByBookmark(@Param("status") String status,@Param("nickname") String nickname);

    @Modifying
    @Query("update Product p set p.views = p.views + 1 where p.productId = :productId")
    void updateByProductId(@Param("productId") String productId);

    @Query("SELECT new com.moaguide.dto.NewDto.customDto.SummaryIssupriceCustomDto(p.productId, pl.category, p.name, (p.nowPiece / p.piece * 100),false) " +
            "FROM Product p JOIN Platform pl on pl.PlatformId = p.PlatformId.PlatformId JOIN IssuePrice ip on ip.id.productId.productId = p.productId " +
            "WHERE pl.PlatformId = p.PlatformId.PlatformId " +
            "AND pl.status = '공모중' " +
            "AND ip.id.productId.productId = p.productId " +
            "AND ip.day <= :date " +
            "ORDER BY ip.day DESC")
    List<SummaryIssupriceCustomDto> findrecent(Pageable pageable,@Param("date")Date date);


    @Query(value = """
        SELECT 
            p.Product_Id AS productId,
            p.name AS name,
            pl.platform AS platform,
            pl.category AS category
        FROM Product p
        JOIN Platform pl ON p.Platform_Id = pl.Platform_Id
        WHERE MATCH(p.name) AGAINST(:keyword IN BOOLEAN MODE)
        LIMIT 10
        """, nativeQuery = true)
    List<Object[]> searchProductsRaw(@Param("keyword") String keyword);
}

