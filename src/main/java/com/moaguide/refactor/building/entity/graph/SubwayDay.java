package com.moaguide.refactor.building.entity.graph;


import com.moaguide.refactor.building.dto.graph.SubwayDto;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Subway_Day")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SqlResultSetMapping(
	name = "subwayDayMapping",
	classes = @ConstructorResult(
		targetClass = SubwayDto.class,
		columns = {
			@ColumnResult(name = "day", type = Date.class),
			@ColumnResult(name = "boarding", type = Integer.class),
			@ColumnResult(name = "alighting", type = Integer.class)
		}
	)
)
@NamedStoredProcedureQuery(
	name = "SubwayDay",
	procedureName = "GetSubwayDay",
	resultSetMappings = "subwayDayMapping",
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "productId", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "date", type = Date.class)
	}
)
public class SubwayDay {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String keyword;

	private Date day;

	private int boarding;

	private int alighting;
}
