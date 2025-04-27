package com.moaguide.refactor.building.entity.graph;


import com.moaguide.refactor.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "land_price")
public class LandPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "product_Id", name = "product_Id")
	private Product productId;

	@Column(name = "land_price")
	private int landPrice;

	@Column(name = "base_year")
	private String baseYear;

	@Column(name = "base_day")
	private String baseDay;

	@Column(name = "notice_day")
	private Date noticeDay;
}

