package com.moaguide.refactor.building.entity.graph;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name="districts")
public class Districts {
    @Id
    @Column(name = "districts_Id")
    private int districtsId;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "borough")
    private String borough;
}
