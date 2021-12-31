package com.example.car_park.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * @Author TuanNA
 * @Date 24/12/2021 5:37 PM
 * @Version 1.0
 */

@Entity
@Data
public class Parkinglot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parkArea;
    private String parkName;
    private String parkPlace;
    private Long parkPrice;
    private String parkStatus;

    @OneToMany(mappedBy = "parkinglot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;
}
