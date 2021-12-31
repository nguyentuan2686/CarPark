package com.example.car_park.service.impl;

import com.example.car_park.config.CarStatus;
import com.example.car_park.dto.CarDTO;
import com.example.car_park.dto.TripDTO;
import com.example.car_park.entity.Car;
import com.example.car_park.entity.Trip;
import com.example.car_park.exception.MyCustomException;
import com.example.car_park.repo.CarRepositoy;
import com.example.car_park.repo.TripRepository;
import com.example.car_park.service.CarService;
import com.example.car_park.service.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 9:59 AM
 * @Version 1.0
 */
@Service
public class TripServiceImpl implements TripService<TripDTO> {

    private TripRepository tripRepository;
    private ModelMapper modelMapper;
    private CarService carService;

    public TripServiceImpl(TripRepository tripRepository, ModelMapper modelMapper, CarService carService) {
        this.tripRepository = tripRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @Override
    public void create(TripDTO tripDTO) {
        String licensePlate = tripDTO.getCarType();
        CarDTO carDTO = carService.getCarByLicensePalte(licensePlate);
        if(carDTO.getCarStatus() == CarStatus.ON_TRIP){
            throw new MyCustomException("Car is on the trip, can't use");
        }
        LocalDate startDate = tripDTO.getDepartureDate();
        LocalDate endDate = tripDTO.getDepartureTime();
        CarStatus carStatus = CarStatus.ON_TRIP;
        Trip trip = modelMapper.map(tripDTO, Trip.class);
        carService.setStatusCar(carStatus, startDate, endDate, licensePlate);
        tripRepository.save(trip);
    }

    @Override
    public TripDTO receive(Long id) {
        return null;
    }

    @Override
    public void update(TripDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TripDTO> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType) {
        Sort sort;
        Page<Trip> tripPage;
        Page<TripDTO> tripDTOPage;
        if(sortField == null && sortType == null){
            sort = Sort.by("id").descending();
        }else if(sortType.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortField).ascending();
        }else {
            sort = Sort.by(sortField).descending();
        }

        if(sizePage==null){
            tripPage = tripRepository.getALL(keyword, Pageable.unpaged());
        }else {
            Pageable pageable = PageRequest.of(currentPage,sizePage,sort);
            tripPage = tripRepository.getALL(keyword, pageable);
        }
        List<Trip> tripList = tripPage.getContent();
        List<TripDTO> tripDTOList = new ArrayList<>();
        tripList.stream().forEach(i -> {
            TripDTO tripDTO = modelMapper.map(i, TripDTO.class);
            tripDTOList.add(tripDTO);
        });
        return tripDTOList;
    }
}
