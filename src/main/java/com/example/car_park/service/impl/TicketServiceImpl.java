package com.example.car_park.service.impl;

import com.example.car_park.dto.CarDTO;
import com.example.car_park.dto.TicketDTO;
import com.example.car_park.entity.Car;
import com.example.car_park.entity.Ticket;
import com.example.car_park.exception.MyCustomException;
import com.example.car_park.repo.TicketRepository;
import com.example.car_park.service.TicketService;
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
 * @Date 29/12/2021 10:03 PM
 * @Version 1.0
 */
@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(TicketDTO ticketDTO) {

        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        ticketRepository.save(ticket);
    }

    @Override
    public TicketDTO receive(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if(optionalTicket.isPresent()){
            TicketDTO ticketDTO = modelMapper.map(optionalTicket.get(), TicketDTO.class);
            return ticketDTO;
        }else {
            throw new MyCustomException("Cant find any ticket");
        }
    }

    @Override
    public void update(TicketDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TicketDTO> getAll(String keyword, Integer currentPage, Integer sizePage, String sortField, String sortType) {
        Sort sort;
        Page<Ticket> ticketPage;
        List<Ticket> ticketList;
        List<TicketDTO> ticketDTOList = new ArrayList<>();
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
            ticketPage = ticketRepository.getAll(keyword, Pageable.unpaged());
        }else {
            if(currentPage == null){
                pageable = PageRequest.of(0,sizePage);
            }else {
                pageable = PageRequest.of(currentPage, sizePage);
            }
            ticketPage = ticketRepository.getAll(keyword, pageable);
        }

        ticketList = ticketPage.getContent();

        ticketList.stream().forEach(i -> {
            TicketDTO ticketDTO = modelMapper.map(i, TicketDTO.class);
            ticketDTOList.add(ticketDTO);
        });

        return ticketDTOList;
    }
}
