package com.example.car_park.service;

import com.example.car_park.dto.ParkinglotDTO;

import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 10:56 AM
 * @Version 1.0
 */
public interface ParkinglotService extends IRootService<ParkinglotDTO>{
    List<ParkinglotDTO> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType);
}
