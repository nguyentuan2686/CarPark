package com.example.car_park.service.impl;

import com.example.car_park.dto.ParkinglotDTO;
import com.example.car_park.entity.Parkinglot;
import com.example.car_park.exception.MyCustomException;
import com.example.car_park.repo.ParkinglotRepository;
import com.example.car_park.service.ParkinglotService;
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
 * @Date 30/12/2021 10:57 AM
 * @Version 1.0
 */
@Service
public class ParkinglotServiceImpl implements ParkinglotService {

    private ParkinglotRepository parkinglotRepository;
    private ModelMapper modelMapper;

    public ParkinglotServiceImpl(ParkinglotRepository parkinglotRepository, ModelMapper modelMapper) {
        this.parkinglotRepository = parkinglotRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(ParkinglotDTO parkinglotDTO) {
        Boolean isExist = parkinglotRepository.findAll().stream().anyMatch( i -> {
            return i.getParkName().equalsIgnoreCase(parkinglotDTO.getParkName())
                    && i.getParkPlace().equalsIgnoreCase(parkinglotDTO.getParkPlace());
        });
        if(isExist){
            throw new MyCustomException("Parkinglot is already exist");
        }else {
            Parkinglot parkinglot = modelMapper.map(parkinglotDTO, Parkinglot.class);
            parkinglotRepository.save(parkinglot);
            List<Parkinglot> list = parkinglotRepository.findAll();
        }
    }

    @Override
    public ParkinglotDTO receive(Long id) {
        Parkinglot parkinglot = new Parkinglot();
        Optional<Parkinglot> optional = parkinglotRepository.findById(id);
        if(optional.isPresent()){
            parkinglot = optional.get();
        }
        ParkinglotDTO parkinglotDTO = modelMapper.map(parkinglot, ParkinglotDTO.class);
        return parkinglotDTO;
    }

    @Override
    public void update(ParkinglotDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ParkinglotDTO> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType) {
        Sort sort;
        Page<Parkinglot> parkinglotPage;
        List<Parkinglot> parkinglotList;
        List<ParkinglotDTO> parkinglotDTOList = new ArrayList<>();
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
            parkinglotPage = parkinglotRepository.getAll(keyword, Pageable.unpaged());
        }else {
            if(currentPage == null){
                pageable = PageRequest.of(0,sizePage);
            }else {
                pageable = PageRequest.of(currentPage, sizePage);
            }
            parkinglotPage = parkinglotRepository.getAll(keyword, pageable);
        }

        parkinglotList = parkinglotPage.getContent();

        parkinglotList.stream().forEach(i -> {
            ParkinglotDTO parkinglotDTO = modelMapper.map(i, ParkinglotDTO.class);
            parkinglotDTOList.add(parkinglotDTO);
        });

        return parkinglotDTOList;
    }
}
