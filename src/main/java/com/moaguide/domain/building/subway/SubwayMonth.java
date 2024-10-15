package com.moaguide.domain.building.subway;


import com.moaguide.dto.NewDto.BuildingDto.SubwayDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "Subway_Month")
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name = "subwayMonthMapping",
        classes = @ConstructorResult(
                targetClass =SubwayDto.class,
                columns = {
                        @ColumnResult(name = "day", type = Date.class),
                        @ColumnResult(name = "boarding", type = Integer.class),
                        @ColumnResult(name = "alighting", type = Integer.class)
                }
        )
)
@Getter
public class SubwayMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String keyword;

    private Date day;

    private int boarding;

    private int alighting;

}
