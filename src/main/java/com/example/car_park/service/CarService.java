package com.example.car_park.service;

import com.example.car_park.config.CarStatus;
import com.example.car_park.dto.CarDTO;
import com.example.car_park.entity.Car;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 1:49 PM
 * @Version 1.0
 */
public interface CarService extends IRootService<CarDTO> {
    List<CarDTO> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType);
    void setStatusCar(CarStatus carStatus, LocalDate startDate, LocalDate endDate, String licensePalte);
    CarDTO getCarByLicensePalte(String licensePlate);
    List<CarDTO> getCarByCarStatus(CarStatus carStatus);
}
