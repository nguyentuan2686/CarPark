package com.example.car_park.repo;

import com.example.car_park.entity.Parkinglot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

/**
 * @Author TuanNA
 * @Date 30/12/2021 10:55 AM
 * @Version 1.0
 */
public interface ParkinglotRepository extends JpaRepository<Parkinglot, Long> {

    @Query(value = "SELECT p FROM Parkinglot p WHERE " +
            "?1 is null or p.parkName like %?1% or p.parkPlace like %?1% or p.parkStatus = ?1")
    Page<Parkinglot> getAll(String keyword, Pageable pageable);
}
