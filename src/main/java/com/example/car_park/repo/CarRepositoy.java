package com.example.car_park.repo;

import com.example.car_park.config.CarStatus;
import com.example.car_park.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 1:48 PM
 * @Version 1.0
 */
public interface CarRepositoy extends JpaRepository<Car, Long> {
    @Query(value = "SELECT c FROM Car c WHERE " +
            "?1 is null or c.carType like %?1% or c.company like %?1%")
    Page<Car> getAll(String keyword, Pageable pageable);

    Boolean existsCarByLicensePlate(String licensePlate);

    @Query(value = "SELECT c FROM Car c WHERE c.licensePlate = ?1")
    Car findCarsByLicensePlate(String licensePlate);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Car c set c.carStatus = ?1, c.dateTripStart = ?2, c.dateTripEnd = ?3 WHERE c.licensePlate = ?4")
//    @Query(value = "UPDATE car SET car_status = ?1, date_trip_end = ?2, date_trip_start = ?3 WHERE license_plate = ?4", nativeQuery = true)
    void setStatusCar(CarStatus carStatus, LocalDate startDate, LocalDate endDate, String licensePlate);

    @Query(value = "SELECT c FROM Car c WHERE c.carStatus = ?1")
    List<Car> findCarByStatus(CarStatus carStatus);
}
