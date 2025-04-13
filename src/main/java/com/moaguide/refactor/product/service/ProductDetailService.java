package com.moaguide.refactor.product.service;


import static com.moaguide.refactor.util.EmptyCheckUtil.isListEmpty;
import static com.moaguide.refactor.util.TimeUtil.getMinusLocalDate;

import com.moaguide.refactor.art.dto.ArtDetailDto;
import com.moaguide.refactor.building.dto.base.BuildingReponseDto;
import com.moaguide.refactor.contents.dto.ContentDetailDto;
import com.moaguide.refactor.cow.dto.HanwooDetailDto;
import com.moaguide.refactor.music.dto.MusicReponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductDetailService {

	private final EntityManager entityManager;

	public ResponseEntity<Object> buildingDetail(String productId, String nickname) {
		StoredProcedureQuery query = entityManager
			.createStoredProcedureQuery("building_detail")
			.registerStoredProcedureParameter("in_Product_Id", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("nickname", String.class, ParameterMode.IN)
			.setParameter("in_Product_Id", productId)
			.setParameter("nickname", nickname);

		// 프로시저 실행
		List<Object[]> resultList = query.getResultList();

		if (isListEmpty(resultList)) {
			return ResponseEntity.ok(new HashMap<>());
		}

		Object[] result = resultList.get(0);  // 첫 번째 결과만 사용

		BuildingReponseDto building = new BuildingReponseDto(
			(String) result[0],
			(String) result[1],
			(String) result[2],
			(String) result[3],
			(Integer) result[4],
			(Double) result[5],
			(Long) result[6],
			(Double) result[7],
			(Double) result[8],
			(Integer) result[9],
			(String) result[10],
			((Long) result[11]) == 1
		);

		return ResponseEntity.ok().body(building);
	}

	public ResponseEntity<Object> musicDetail(String productId, String nickname) {
		LocalDate year = getMinusLocalDate(1);
		StoredProcedureQuery query = entityManager
			.createStoredProcedureQuery("music_detail")
			.registerStoredProcedureParameter("in_Product_Id", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("nickname", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("year", int.class, ParameterMode.IN)
			.setParameter("in_Product_Id", productId)
			.setParameter("nickname", nickname)
			.setParameter("year", year);

		List<Object[]> resultList = query.getResultList();

		if (isListEmpty(resultList)) {
			return ResponseEntity.ok(new HashMap<>());
		}

		Object[] result = resultList.get(0);

		MusicReponseDto music = new MusicReponseDto(
			(String) result[0],  // productId
			(String) result[1],  // category
			(String) result[2],  // platform
			(String) result[3],  // name
			(String) result[4],  // singer
			((Integer) result[5]),  // price (Integer 그대로 사용)
			((BigDecimal) result[6]).doubleValue(),  // priceRate (Double 그대로 사용)
			((Long) result[7]),  // totalPrice
			((BigDecimal) result[8]).doubleValue(),  // lastDivide (Double 그대로 사용)
			((BigDecimal) result[9]).doubleValue(),  // lastDivideRate (Double 그대로 사용)
			((Integer) result[10]),  // divideCycle (Integer 그대로 사용)
			(String) result[11],  // link
			((Long) result[12]) == 1,  // bookmark: Long 값을 Boolean으로 변환
			((Integer) result[13]),  // yearDivide (Integer 그대로 사용)
			((BigDecimal) result[14]).doubleValue()  // yearDivideRate (Double 그대로 사용)
		);
		return ResponseEntity.ok().body(music);
	}

	public ResponseEntity<Object> contentsDetail(String productId, String nickname) {
		StoredProcedureQuery query = entityManager
			.createStoredProcedureQuery("GetContentpublish")
			.registerStoredProcedureParameter("in_Id", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("nickname", String.class, ParameterMode.IN)
			.setParameter("in_Id", productId)
			.setParameter("nickname", nickname);

		// 프로시저 실행
		List<Object[]> resultList = query.getResultList();

		if (isListEmpty(resultList)) {
			return ResponseEntity.ok(new HashMap<>());
		}

		Object[] result = resultList.get(0);  // 첫 번째 결과만 사용

		ContentDetailDto contentDetailDto = new ContentDetailDto(
			(String) result[0],
			(String) result[1],
			(String) result[2],
			(String) result[3],
			(String) result[4],
			(Long) result[5],
			(Double) result[6],
			(Date) result[7],
			(Integer) result[8],
			(String) result[9],
			((Integer) result[10]) == 1
		);

		return ResponseEntity.ok().body(contentDetailDto);
	}

	public ResponseEntity<Object> artDetail(String productId, String nickname) {
		StoredProcedureQuery query = entityManager
			.createStoredProcedureQuery("art_details")
			.registerStoredProcedureParameter("in_Product_Id", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("nickname", String.class, ParameterMode.IN)
			.setParameter("in_Product_Id", productId)
			.setParameter("nickname", nickname);

		// 프로시저 실행
		List<Object[]> resultList = query.getResultList();

		// 결과가 없을 경우 null 리턴
		if (isListEmpty(resultList)) {
			return ResponseEntity.ok(new HashMap<>());
		}

		// 결과값을 DTO에 매핑
		Object[] result = resultList.get(0);  // 첫 번째 결과만 사용

		// ArtDetailDto 매핑
		ArtDetailDto art = new ArtDetailDto(
			(String) result[0],  // productId
			(String) result[1],  // category
			(String) result[2],  // platform
			(String) result[3],  // name
			(String) result[4],  // authorName
			((Long) result[5]),  // recruitmentPrice (Integer 그대로 사용)
			((BigDecimal) result[6]).doubleValue(),  // recruitmentRate (Double 그대로 사용)
			((Long) result[7]),  // totalPrice
			String.valueOf(result[8]),  // subscriptionDate
			((Integer) result[9]),  // minInvestment (Integer 그대로 사용)
			(String) result[10],
			((Long) result[11]) == 1 // isBookmarked: Long 값을 Boolean으로 변환
		);
		return ResponseEntity.ok().body(art);
	}

	public ResponseEntity<Object> cowDetail(String productId, String nickname) {
		StoredProcedureQuery query = entityManager
			.createStoredProcedureQuery("hanwoo_detail")
			.registerStoredProcedureParameter("in_Product_Id", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("nickname", String.class, ParameterMode.IN)
			.setParameter("in_Product_Id", productId)
			.setParameter("nickname", nickname);

		// 프로시저 실행
		List<Object[]> resultList = query.getResultList();

		if (isListEmpty(resultList)) {
			return ResponseEntity.ok(new HashMap<>());
		}

		Object[] result = resultList.get(0);  // 첫 번째 결과만 사용

		HanwooDetailDto cow = new HanwooDetailDto(
			(String) result[0],  // productId
			(String) result[1],  // category
			(String) result[2],  // platform
			(String) result[3],  // title
			(String) result[4],  // name
			((Long) result[5]),  // recruitmentPrice (Integer 그대로 사용)
			((BigDecimal) result[6]).doubleValue(),  // recruitmentRate (Double 그대로 사용)
			((Long) result[7]),  // totalPrice
			((Date) result[8]).toLocalDate(),  // paymentDate
			((Integer) result[9]),  // minInvestment
			(String) result[10], // link
			((Long) result[11]) == 1 // isBookmarked: Long 값을 Boolean으로 변환
		);

		return ResponseEntity.ok().body(cow);
	}
}
