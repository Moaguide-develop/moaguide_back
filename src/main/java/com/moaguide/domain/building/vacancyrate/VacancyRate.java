package com.moaguide.domain.building.vacancyrate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Vacancy_Rate")
public class VacancyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String keyword;
    private String region;
    private int year;
    private int quarter;
    @Column(name = "vacancy_rate")
    private double vacancyRate;
    private String type;

}
