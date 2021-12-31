package com.example.car_park.controller;

import com.example.car_park.dto.EmployeeDTO;
import com.example.car_park.dto.ResponseModel;
import com.example.car_park.dto.TripDTO;
import com.example.car_park.service.TripService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 10:01 AM
 * @Version 1.0
 */

@RestController
@RequestMapping("/trip")
public class TripController {
    private TripService tripService;
    ResponseModel responseModel = new ResponseModel();

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseModel<TripDTO> getTripById(@PathVariable(value = "id") Long id){
        responseModel.setData(tripService.receive(id));
        return responseModel;
    }

    @GetMapping("/get-all")
    public ResponseModel<List<TripDTO>> getAllTrip(@RequestParam(value="keyword", required = false) String keyword,
                                                           @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                           @RequestParam(value = "sizePage", required = false)  Integer sizePage,
                                                           @RequestParam(value = "sortField", required = false) String sortField,
                                                           @RequestParam(value = "sortType", required = false) String sortType){
        List<TripDTO> list = tripService.getAll(keyword, currentPage, sizePage, sortField, sortType );
        responseModel.setData(list);
        return responseModel;
    }

    @PostMapping("/add-trip")
    public ResponseModel<Void> addTrip(@Valid @RequestBody TripDTO tripDTO){
        tripService.create(tripDTO);
        return responseModel;
    }

    @PutMapping("/edit-trip")
    public ResponseModel<Void> editTrip(@Valid @RequestBody TripDTO tripDTO){
        tripService.update(tripDTO);
        return responseModel;
    }

    @DeleteMapping("/delete-trip")
    public ResponseModel<Void> deleteTrip(@RequestParam(value = "id", required = false) Long id){
        tripService.delete(id);
        return responseModel;
    }
}
