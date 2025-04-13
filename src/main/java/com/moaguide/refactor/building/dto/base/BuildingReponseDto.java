package com.moaguide.refactor.building.dto.base;


import java.beans.ConstructorProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class BuildingReponseDto {

	private String product_Id;
	private String category;
	private String platform;
	private String name;
	private Integer price;
	private Double priceRate;
	private Long totalPrice;
	private Double lastDivide;
	private Double lastDivide_rate;
	private Integer divideCycle;
	private String link;
	private Boolean bookmark;
	private Boolean rentType;
	private Boolean stayType;


	@ConstructorProperties({"product_Id", "category", "platform", "name", "price", "priceRate",
		"totalPrice", "lastDivide", "lastDivide_rate", "divideCycle", "link", "bookmark"})
	public BuildingReponseDto(String product_Id, String category, String platform, String name,
		Integer price, Double priceRate, Long totalPrice, Double lastDivide, Double lastDivide_rate,
		Integer divideCycle, String link, Boolean bookmark) {
		this.product_Id = product_Id;
		this.category = category;
		this.platform = platform;
		this.name = name;
		this.price = price;
		this.priceRate = priceRate;
		this.totalPrice = totalPrice;
		this.lastDivide = lastDivide;
		this.lastDivide_rate = lastDivide_rate;
		this.divideCycle = divideCycle;
		this.link = link;
		this.bookmark = bookmark;
	}
}
