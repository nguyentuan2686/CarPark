package com.example.car_park.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author TuanNA
 * @Date 23/12/2021 10:46 PM
 * @Version 1.0
 */
public interface EmployeeService<T> extends IRootService<T> {

    List<T> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType);
}
