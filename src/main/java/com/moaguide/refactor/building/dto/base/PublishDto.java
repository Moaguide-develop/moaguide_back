package com.moaguide.refactor.building.dto.base;

import com.moaguide.refactor.building.entity.BuildingDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PublishDto {

	private String name;
	private String publisher; // 증권종류
	private int piece; // 증권수
	private Double last_divide;
	private int basePrice; // 1주당 발행액
	private long totalPrice;
	private String subscription; // 청약일정 YYYY.MM.DD ~ YYYY.MM.DD
	private String listingDate; // 상장일 YYYY-MM-DD

	public PublishDto(BuildingDetail buildingDetail, double dividend) {
		this.name = buildingDetail.getProductId().getName();
		this.publisher = getPublisher();
		this.last_divide = dividend;
		this.piece = buildingDetail.getProductId().getPiece();
		this.subscription = buildingDetail.getProductId().getName();
		this.listingDate = getListingDate();
	}
}
