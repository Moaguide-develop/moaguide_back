package com.moaguide.refactor.building.entity;

import com.moaguide.refactor.building.dto.base.BuildingBaseDto;
import com.moaguide.refactor.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Land_Registry")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SqlResultSetMapping(
	name = "BuildingBaseDtoMapping",
	classes = @ConstructorResult(
		targetClass = BuildingBaseDto.class,
		columns = {
			@ColumnResult(name = "name", type = String.class),
			@ColumnResult(name = "useArea", type = String.class),
			@ColumnResult(name = "mainUse", type = String.class),
			@ColumnResult(name = "completionDate", type = String.class),
			@ColumnResult(name = "landArea", type = String.class),
			@ColumnResult(name = "floorArea", type = String.class),
			@ColumnResult(name = "floorAreaRate", type = Double.class),
			@ColumnResult(name = "dryRatio", type = Double.class),
			@ColumnResult(name = "height", type = Double.class),
			@ColumnResult(name = "scale", type = String.class),
			@ColumnResult(name = "mainStructure", type = String.class),
			@ColumnResult(name = "parking", type = Integer.class),
			@ColumnResult(name = "lift", type = Integer.class),
			@ColumnResult(name = "location", type = String.class),
			@ColumnResult(name = "landElevation", type = String.class),
			@ColumnResult(name = "landShape", type = String.class),
			@ColumnResult(name = "roadInterface", type = String.class),
			@ColumnResult(name = "zoningNational", type = String.class),
			@ColumnResult(name = "zoningOther", type = String.class),
			@ColumnResult(name = "publisher", type = String.class),
			@ColumnResult(name = "piece", type = Integer.class),
			@ColumnResult(name = "lastDivide", type = Double.class),
			@ColumnResult(name = "basePrice", type = Integer.class),
			@ColumnResult(name = "totalPrice", type = long.class),
			@ColumnResult(name = "subscription", type = String.class),
			@ColumnResult(name = "listingDate", type = String.class),
			@ColumnResult(name = "divideCycle", type = Integer.class),
			@ColumnResult(name = "divideRate", type = Double.class),
			@ColumnResult(name = "paymentDay", type = Date.class)
		}
	)
)
@NamedStoredProcedureQuery(
	name = "BuildingBaseProcedure",
	procedureName = "building_base",
	resultSetMappings = "BuildingBaseDtoMapping",
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_Product_Id", type = String.class)
	}
)
public class LandRegistry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false)
	private Product productId;

	@Column(name = "Land_Elevation", length = 50)
	private String landElevation;

	@Column(name = "Land_Shape", length = 50)
	private String landShape;

	@Column(name = "Road_Interface", length = 50)
	private String roadInterface;

	@Column(name = "Zoning_National", columnDefinition = "TEXT")
	private String zoningNational;

	@Column(name = "Zoning_Other", columnDefinition = "TEXT")
	private String zoningOther;


	// 새로운 생성자 추가
	public LandRegistry(String landElevation, String landShape, String roadInterface,
		String zoningNational, String zoningOther) {
		this.landElevation = landElevation;
		this.landShape = landShape;
		this.roadInterface = roadInterface;
		this.zoningNational = zoningNational;
		this.zoningOther = zoningOther;
	}
}
