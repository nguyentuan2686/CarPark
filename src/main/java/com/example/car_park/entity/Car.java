package com.example.car_park.entity;

import com.example.car_park.config.CarStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 24/12/2021 3:15 PM
 * @Version 1.0
 */
@Entity
@Getter
@Setter
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String licensePlate;

    private String carColor;
    private String carType;
    private String company;
    private CarStatus carStatus;
    private LocalDate dateTripStart;
    private LocalDate dateTripEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "park_id")
    @JsonIgnore
    private Parkinglot parkinglot;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

}
