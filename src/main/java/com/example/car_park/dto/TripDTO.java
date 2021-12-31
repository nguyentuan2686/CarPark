package com.example.car_park.dto;

import com.example.car_park.entity.Car;
import com.example.car_park.entity.Ticket;
import com.example.car_park.entity.Bookingoffice;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 9:53 AM
 * @Version 1.0
 */
@Data
public class TripDTO {
    private Long id;
    private Integer bookedTicketNumber;
    private String carType;
    private LocalDate departureDate;
    private LocalDate departureTime;
    private String destination;
    private String driver;
    private Integer maxOnlineTicketNumber;
    private List<Ticket> ticketList;
    private List<Bookingoffice> bookingofficeList;
    private Car car;
}
