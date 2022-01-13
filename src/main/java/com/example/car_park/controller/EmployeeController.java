package com.example.car_park.controller;

import com.example.car_park.dto.EmployeeDTO;
import com.example.car_park.dto.ResponseModel;
import com.example.car_park.service.EmployeeService;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @Author TuanNA
 * @Date 24/12/2021 9:12 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final MessageSource messageSource;
    ResponseModel responseModel = new ResponseModel();

    public EmployeeController(EmployeeService employeeService, MessageSource messageSource){
        this.employeeService = employeeService;
        this.messageSource = messageSource;
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseModel<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long id){
        responseModel.setData(employeeService.receive(id));
        return responseModel;
    }

    //Internationalization
    @GetMapping("/hello-world")
    public ResponseModel<String> hello(@RequestHeader(name = "Accept-Language", required = false) Locale locale){

        ResponseModel<String> responseModel = new ResponseModel<>();
        String hello = messageSource.getMessage("hello.massage", null, "Default is: xin chào", locale);
        responseModel.setData(hello);
        return responseModel;
    }

    @GetMapping("employee/{id}")
    public EntityModel<EmployeeDTO> retrieveUser(@PathVariable Long id){
        EmployeeDTO employeeDTO = employeeService.receive(id);
        EntityModel<EmployeeDTO> entityModel = EntityModel.of(employeeDTO);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllEmployee(null, null,null,null,null));
        entityModel.add(linkBuilder.withRel("all-employee"));
        return  entityModel;
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
