package com.example.car_park.service;

import com.example.car_park.dto.TicketDTO;

import java.util.List;

/**
 * @Author TuanNA
 * @Date 29/12/2021 10:02 PM
 * @Version 1.0
 */
public interface TicketService extends IRootService<TicketDTO>{
    List<TicketDTO> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType);
}
