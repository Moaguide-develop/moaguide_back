package com.moaguide.refactor.building.entity.sub;


import com.moaguide.refactor.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Business_Area")
public class BusinessArea {

	@Id
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false)
	private Product productId;

	@Column(name = "CBD")
	private String cbd;

	@Column(name = "CBD_Distance")
	private String cbdDistance;

	@Column(name = "CBD_Car")
	private String cbdCar;

	@Column(name = "CBD_Subway")
	private String cbdSubway;

	@Column(name = "GBD")
	private String gbd;

	@Column(name = "GBD_Distance")
	private String gbdDistance;

	@Column(name = "GBD_Car")
	private String gbdCar;

	@Column(name = "GBD_Subway")
	private String gbdSubway;

	@Column(name = "YBD")
	private String ybd;

	@Column(name = "YBD_Distance")
	private String ybdDistance;

	@Column(name = "YBD_Car")
	private String ybdCar;

	@Column(name = "YBD_Subway")
	private String ybdSubway;

}
