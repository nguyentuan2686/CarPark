package com.example.car_park.service.impl;

import com.example.car_park.dto.EmployeeDTO;
import com.example.car_park.entity.Employee;
import com.example.car_park.exception.MyCustomException;
import com.example.car_park.repo.EmployeeRepository;
import com.example.car_park.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author TuanNA
 * @Date 23/12/2021 10:48 PM
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(EmployeeDTO dto) {
        Employee employee = modelMapper.map(dto, Employee.class);
        List<Employee> list = employeeRepository.findAll();
        for(int i = 0; i <list.size(); i++){
            if(list.get(i).getAccount().equalsIgnoreCase(employee.getAccount())){

            }
        }
        boolean isExist = list.stream().anyMatch(i -> i.getAccount().equalsIgnoreCase(employee.getAccount()));
        if(isExist){
            throw new MyCustomException("Employee is already exist");
        }
//        list.forEach(i->{
//            if(i.getAccount().equalsIgnoreCase(employee.getAccount())){
//                throw new GeneralException("Employee is already exist");
//            }
//        });

        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDTO receive(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            return employeeDTO;
        }
        return null;
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        List<Employee> employees = employeeRepository.findAll();

        for (Employee value : employees) {
            if (value.getId() == employee.getId()) continue;
            else {
                if (value.getAccount().equalsIgnoreCase(employee.getAccount())) {
                    throw new MyCustomException("Account is already exist");
                }
            }
        }
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        List<Employee> employees = employeeRepository.findAll();
        Boolean isExist = employees.stream().anyMatch(i -> i.getId()==id);
        if(!isExist){
            throw new MyCustomException("Can't match any id");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType) {
        Integer totalEmployee = employeeRepository.findAll().size();
        Sort sort;
        if(sortField==null&&sortType==null){
            sort = Sort.by("id").descending();
        } else if(sortType.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortField).ascending();
        }else {
           sort = Sort.by(sortField).descending();
        }
        Page<Employee> page;
        if(sizePage == null){
            page = employeeRepository.getAll(keyword, Pageable.unpaged());
        }else {
            Pageable pageable = PageRequest.of(currentPage, sizePage,sort);
            page = employeeRepository.getAll(keyword, pageable);
        }
        List<Employee> employees = page.getContent();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.stream().forEach(i -> {
            EmployeeDTO employeeDTO = modelMapper.map(i, EmployeeDTO.class);
            employeeDTOS.add(employeeDTO);
        });
        return employeeDTOS;
    }
}
