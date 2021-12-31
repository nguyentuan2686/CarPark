package com.example.car_park.service.impl;

import com.example.car_park.config.CarStatus;
import com.example.car_park.dto.CarDTO;
import com.example.car_park.dto.ParkinglotDTO;
import com.example.car_park.entity.Car;
import com.example.car_park.entity.Parkinglot;
import com.example.car_park.exception.MyCustomException;
import com.example.car_park.repo.CarRepositoy;
import com.example.car_park.repo.ParkinglotRepository;
import com.example.car_park.service.CarService;
import com.example.car_park.service.ParkinglotService;
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
import java.util.Optional;

/**
 * @Author TuanNA
 * @Date 30/12/2021 1:49 PM
 * @Version 1.0
 */
@Service
public class CarServiceImpl implements CarService {

    private CarRepositoy carRepositoy;
    private ModelMapper modelMapper;
    private ParkinglotService parkinglotService;

    public CarServiceImpl(CarRepositoy carRepositoy, ModelMapper modelMapper, ParkinglotService parkinglotService) {
        this.carRepositoy = carRepositoy;
        this.modelMapper = modelMapper;
        this.parkinglotService = parkinglotService;
    }


    @Override
    public List<CarDTO> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType) {
        Sort sort;
        Page<Car> carPage;
        List<Car> carList;
        List<CarDTO> carDTOList = new ArrayList<>();
        Pageable pageable;
        if(sortField == null){
            if(sortType == null || sortType.equalsIgnoreCase("ASC")){
                sort = Sort.by("id").ascending();
            }else {
                sort = Sort.by("id").descending();
            }
        }else {
            if(sortType == null || sortType.equalsIgnoreCase("ASC")){
                sort = Sort.by(sortField).ascending();
            }else {
                sort = Sort.by(sortField).descending();
            }
        }
        if(sizePage == null){
            carPage = carRepositoy.getAll(keyword, Pageable.unpaged());
        }else {
            if(currentPage == null){
                pageable = PageRequest.of(0,sizePage);
            }else {
                pageable = PageRequest.of(currentPage, sizePage);
            }
            carPage = carRepositoy.getAll(keyword, pageable);
        }

        carList = carPage.getContent();

        carList.stream().forEach(i -> {
            CarDTO carDTO = modelMapper.map(i, CarDTO.class);
            carDTOList.add(carDTO);
        });

        return carDTOList;
    }

    @Override
    public void setStatusCar(CarStatus carStatus, LocalDate startDate, LocalDate endDate, String licensePlate) {
        carRepositoy.setStatusCar(carStatus, startDate, endDate, licensePlate);
    }

    @Override
    public CarDTO getCarByLicensePalte(String licensePlate) {
        Car car = carRepositoy.findCarsByLicensePlate(licensePlate);
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        return carDTO;
    }

    @Override
    public List<CarDTO> getCarByCarStatus(CarStatus carStatus) {
        List<Car> carList = carRepositoy.findCarByStatus(carStatus);
        List<CarDTO> carDTOList = new ArrayList<>();
        carList.stream().forEach(i -> {
            CarDTO carDTO = modelMapper.map(i, CarDTO.class);
            carDTOList.add(carDTO);
        });
        return carDTOList;
    }

    @Override
    public void create(CarDTO carDTO) {
        Boolean exist = carRepositoy.existsCarByLicensePlate(carDTO.getLicensePlate());
        if(exist){
            throw new MyCustomException("License Plate car is already exist");
        }
        ParkinglotDTO parkinglotDTO = parkinglotService.receive(carDTO.getParkinglotId());
        Parkinglot parkinglot = modelMapper.map(parkinglotDTO, Parkinglot.class);
        carDTO.setParkinglot(parkinglot);
        Car car = modelMapper.map(carDTO, Car.class);
        carRepositoy.save(car);
    }

    @Override
    public CarDTO receive(Long id) {
        Optional<Car> optionalCar = carRepositoy.findById(id);
        if(optionalCar.isPresent()){
            CarDTO carDTO = modelMapper.map(optionalCar.get(), CarDTO.class);
            return carDTO;
        }else {
            throw new MyCustomException("Cant find any car");
        }
    }

    @Override
    public void update(CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        List<Car> listCar = carRepositoy.findAll();
        for(int i = 0; i < listCar.size(); i++){
            if(listCar.get(i).getId() == carDTO.getId()) continue;
            else {
                if(listCar.get(i).getLicensePlate().equalsIgnoreCase(carDTO.getLicensePlate())){
                    throw new MyCustomException("License Plate is already exist");
                }
            }
        }
        carRepositoy.save(car);
    }

    @Override
    public void delete(Long id) {

    }
}
