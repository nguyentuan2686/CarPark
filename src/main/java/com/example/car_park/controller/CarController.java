package com.example.car_park.controller;

import com.example.car_park.config.CarStatus;
import com.example.car_park.dto.CarDTO;
import com.example.car_park.dto.EmployeeDTO;
import com.example.car_park.dto.ResponseModel;
import com.example.car_park.service.CarService;
import com.example.car_park.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 1:48 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;
    ResponseModel responseModel = new ResponseModel();

    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseModel<CarDTO> getCarById(@PathVariable(value = "id") Long id){
        responseModel.setData(carService.receive(id));
        return responseModel;
    }

    @GetMapping("/get-all")
    public ResponseModel<List<CarDTO>> getAllCar(@RequestParam(value="keyword", required = false) String keyword,
                                                           @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                           @RequestParam(value = "sizePage", required = false)  Integer sizePage,
                                                           @RequestParam(value = "sortField", required = false) String sortField,
                                                           @RequestParam(value = "sortType", required = false) String sortType){
        List<CarDTO> list = carService.getAll(keyword, currentPage, sizePage, sortField, sortType );
        responseModel.setData(list);
        return responseModel;
    }

    @GetMapping("get-by-status")
    public ResponseModel<List<CarDTO>> getAllCarByStatus(@RequestParam(value = "status") CarStatus carStatus){
        List<CarDTO> list = carService.getCarByCarStatus(carStatus);
        responseModel.setData(list);
        return responseModel;
    }

    @PostMapping("/add-car")
    public ResponseModel<Void> addCar(@Valid @RequestBody CarDTO carDTO){
        carService.create(carDTO);
        return responseModel;
    }

    @PutMapping("/edit-car")
    public ResponseModel<Void> editCar(@Valid @RequestBody CarDTO carDTO){
        carService.update(carDTO);
        return responseModel;
    }

    @DeleteMapping("/delete-car")
    public ResponseModel<Void> deleteEmployee(@RequestParam(value = "id", required = false) Long id){
        carService.delete(id);
        return responseModel;
    }
}
