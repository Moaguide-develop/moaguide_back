package com.moaguide.refactor.building.entity.subway;


import com.moaguide.refactor.building.dto.graph.SubwayDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

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
