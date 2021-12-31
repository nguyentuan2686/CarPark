package com.example.car_park.controller;

import com.example.car_park.dto.EmployeeDTO;
import com.example.car_park.dto.ResponseModel;
import com.example.car_park.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 24/12/2021 9:12 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    ResponseModel responseModel = new ResponseModel();

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseModel<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long id){
        responseModel.setData(employeeService.receive(id));
        return responseModel;
    }

    @GetMapping("/get-all")
    public ResponseModel<List<EmployeeDTO>> getAllEmployee(@RequestParam(value="keyword", required = false) String keyword,
                                                           @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                           @RequestParam(value = "sizePage", required = false)  Integer sizePage,
                                                           @RequestParam(value = "sortField", required = false) String sortField,
                                                           @RequestParam(value = "sortType", required = false) String sortType){
        List<EmployeeDTO> list = employeeService.getAll(keyword, currentPage, sizePage, sortField, sortType );
        responseModel.setData(list);
        return responseModel;
    }

    @PostMapping("/add-employee")
    public ResponseModel<Void> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.create(employeeDTO);
        return responseModel;
    }

    @PutMapping("/edit-employee")
    public ResponseModel<Void> editEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.update(employeeDTO);
        return responseModel;
    }

    @DeleteMapping("/delete-employee")
    public ResponseModel<Void> deleteEmployee(@RequestParam(value = "id", required = false) Long id){
        employeeService.delete(id);
        return responseModel;
    }
}
