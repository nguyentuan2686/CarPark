package com.example.car_park.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 24/12/2021 3:22 PM
 * @Version 1.0
 */
@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer bookedTicketNumber;
    private String carType;
    private LocalDate departureDate;
    private LocalDate departureTime;
    private String destination;
    private String driver;
    private Integer maxOnlineTicketNumber;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> ticketList;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bookingoffice> bookingofficeList;
}
