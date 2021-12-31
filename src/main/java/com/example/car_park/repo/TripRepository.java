package com.example.car_park.repo;

import com.example.car_park.entity.Employee;
import com.example.car_park.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author TuanNA
 * @Date 30/12/2021 10:00 AM
 * @Version 1.0
 */
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query(value = "SELECT t FROM Trip t WHERE " +
            "?1 is null or t.destination like %?1% or t.driver like %?1%")
    Page<Trip> getALL(String keyword, Pageable pageable);
}
