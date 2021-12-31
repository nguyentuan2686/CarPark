package com.example.car_park.dto;

import com.example.car_park.config.CarStatus;
import com.example.car_park.entity.Parkinglot;
import com.example.car_park.entity.Ticket;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 1:48 PM
 * @Version 1.0
 */
@Data
public class CarDTO {
    private Long id;
    private String licensePlate;
    private String carColor;
    private String carType;
    private String company;
    private CarStatus carStatus;
    private LocalDate dateTripStart;
    private LocalDate dateTripEnd;
    private Long parkinglotId;
    private Parkinglot parkinglot;
    private List<Ticket> ticketList;
}
