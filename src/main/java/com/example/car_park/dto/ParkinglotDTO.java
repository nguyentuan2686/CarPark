package com.example.car_park.dto;

import com.example.car_park.entity.Car;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @Author TuanNA
 * @Date 30/12/2021 10:53 AM
 * @Version 1.0
 */
@Data
public class ParkinglotDTO {
    private Long id;
    private Long parkArea;
    private String parkName;
    private String parkPlace;
    private Long parkPrice;
    private String parkStatus;
    private List<Car> cars;
}
