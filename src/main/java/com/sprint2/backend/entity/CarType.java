package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "car_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "car_type_name", columnDefinition = "VARCHAR(50)")
    private String carTypeName;

    // relationship

    @OneToMany(mappedBy = "carType", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Car> carList;
}
