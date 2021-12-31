package com.example.car_park.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @Author TuanNA
 * @Date 24/12/2021 5:42 PM
 * @Version 1.0
 */
@Entity
@Data
public class Bookingoffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate endContractDeadline;
    private String officeName;
    private String officePhone;
    private String officePlace;
    private Long officePrice;
    private LocalDate startContractDeadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="trip_id")
    private Trip trip;
}
