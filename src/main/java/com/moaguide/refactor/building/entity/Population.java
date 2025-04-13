package com.moaguide.refactor.building.entity;

import com.moaguide.refactor.building.dto.graph.PopulationDto;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "population")
@SqlResultSetMapping(
	name = "PopulationDtoMapping",
	classes = @ConstructorResult(
		targetClass = PopulationDto.class,
		columns = {
			@ColumnResult(name = "weekDay", type = String.class),
			@ColumnResult(name = "total", type = Integer.class),
			@ColumnResult(name = "age0", type = Integer.class),
			@ColumnResult(name = "age10", type = Integer.class),
			@ColumnResult(name = "age20", type = Integer.class),
			@ColumnResult(name = "age30", type = Integer.class),
			@ColumnResult(name = "age40", type = Integer.class),
			@ColumnResult(name = "age50", type = Integer.class),
			@ColumnResult(name = "age60", type = Integer.class),
			@ColumnResult(name = "age70", type = Integer.class),
			@ColumnResult(name = "man", type = Integer.class),
			@ColumnResult(name = "girl", type = Integer.class),
		}
	)
)
@NamedStoredProcedureQuery(
	name = "populateProcedure",
	procedureName = "populate",
	resultSetMappings = "PopulationDtoMapping",
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_day", type = Date.class)
	}
)
public class Population {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "districts_Id", nullable = true)
	private Districts districts;

	@Temporal(TemporalType.DATE)
	private Date day;

	@Column(name = "week_day")
	private String weekDay;

	private Integer total;

	private Integer age0;

	private Integer age10;

	private Integer age20;

	private Integer age30;

	private Integer age40;

	private Integer age50;

	private Integer age60;

	private Integer age70;

	private Integer man;

	private Integer girl;
}
