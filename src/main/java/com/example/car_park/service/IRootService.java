package com.example.car_park.service;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author TuanNA
 * @Date 23/12/2021 9:59 PM
 * @Version 1.0
 */

public interface IRootService<T> {
    void create(T dto);
    T receive(Long id);
    void update(T dto);
    void delete(Long id);

}
