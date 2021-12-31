package com.example.car_park.service;

import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 9:59 AM
 * @Version 1.0
 */
public interface TripService<T> extends IRootService<T>{
    List<T> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType);
}
