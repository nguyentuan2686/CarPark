package com.example.car_park.repo;

import com.example.car_park.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author TuanNA
 * @Date 29/12/2021 10:01 PM
 * @Version 1.0
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT t FROM Ticket t WHERE" +
            "(?1 is null or t.trip.destination like %?1% or t.car.licensePlate like %?1%)")
    Page<Ticket> getAll(String keyword, Pageable pageable);
}
