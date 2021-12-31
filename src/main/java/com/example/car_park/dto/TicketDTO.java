package com.example.car_park.dto;

import com.example.car_park.entity.Car;
import com.example.car_park.entity.Trip;
import lombok.Data;

import java.time.LocalTime;

/**
 * @Author TuanNA
 * @Date 29/12/2021 10:04 PM
 * @Version 1.0
 */
@Data
public class TicketDTO {
    private Long id;
    private LocalTime bookingTime;
    private String customerName;
    private Trip trip;
    private Long tripId;
    private Car car;
    private Long carId;
}
