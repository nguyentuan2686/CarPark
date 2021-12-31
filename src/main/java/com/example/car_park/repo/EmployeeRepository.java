package com.example.car_park.repo;

import com.example.car_park.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author TuanNA
 * @Date 23/12/2021 9:58 PM
 * @Version 1.0
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT e FROM Employee e WHERE" +
            "(?1 is null or e.name like %?1% or e.account like %?1% or e.address like %?1% or e.email like %?1%)")
    Page<Employee> getAll(String keyword, Pageable pageable);
}
