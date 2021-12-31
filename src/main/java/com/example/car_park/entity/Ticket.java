package com.example.car_park.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * @Author TuanNA
 * @Date 24/12/2021 3:10 PM
 * @Version 1.0
 */
@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime bookingTime;
    private String customerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license_plate", referencedColumnName = "licensePlate")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
