package com.example.car_park.controller;

import com.example.car_park.dto.ParkinglotDTO;
import com.example.car_park.dto.ResponseModel;
import com.example.car_park.service.ParkinglotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author TuanNA
 * @Date 30/12/2021 11:41 AM
 * @Version 1.0
 */
@RestController
@RequestMapping("/parkinglot")
public class ParkinglotController {

    private ParkinglotService parkinglotService;
    ResponseModel responseModel = new ResponseModel();

    public ParkinglotController(ParkinglotService parkinglotService) {
        this.parkinglotService = parkinglotService;
    }

    @GetMapping("/get-all")
    public ResponseModel<List<ParkinglotDTO>> getAll(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                     @RequestParam(value = "sizePage", required = false)  Integer sizePage,
                                                     @RequestParam(value = "sortField", required = false) String sortField,
                                                     @RequestParam(value = "sortType", required = false) String sortType){
        List<ParkinglotDTO> parkinglotDTOList = parkinglotService.getAll(keyword, currentPage, sizePage, sortField, sortType);
        responseModel.setData(parkinglotDTOList);
        return responseModel;
    }

    @PostMapping("/add-parkinglot")
    public ResponseModel<Void> addParkinglot(@RequestBody ParkinglotDTO parkinglotDTO){
        parkinglotService.create(parkinglotDTO);
        return responseModel;
    }
}
