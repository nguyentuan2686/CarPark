package com.example.car_park.controller;

import com.example.car_park.dto.ResponseModel;
import com.example.car_park.dto.TicketDTO;
import com.example.car_park.service.TicketService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author TuanNA
 * @Date 29/12/2021 11:04 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;
    ResponseModel responseModel = new ResponseModel();

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseModel<TicketDTO> getTicketById(@PathVariable(value = "id") Long id){
        responseModel.setData(ticketService.receive(id));
        return responseModel;
    }

    @GetMapping("/get-all")
    public ResponseModel<List<TicketDTO>> getAllTicket(@RequestParam(value = "keyword", required = false) String keyword,
                                                         @RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                           @RequestParam(value = "sizePage", required = false)  Integer sizePage,
                                                           @RequestParam(value = "sortField", required = false) String sortField,
                                                           @RequestParam(value = "sortType", required = false) String sortType){
        List<TicketDTO> list = ticketService.getAll(keyword, currentPage, sizePage, sortField, sortType );
        responseModel.setData(list);
        return responseModel;
    }

    @PostMapping("/add-ticket")
    public ResponseModel<Void> addEmployee(@Valid @RequestBody TicketDTO ticketDTO){
        ticketService.create(ticketDTO);
        return responseModel;
    }

    @PutMapping("/edit-ticket")
    public ResponseModel<Void> editEmployee(@Valid @RequestBody TicketDTO ticketDTO){
        ticketService.update(ticketDTO);
        return responseModel;
    }

    @DeleteMapping("/delete-ticket")
    public ResponseModel<Void> deleteEmployee(@RequestParam(value = "id", required = false) Long id){
        ticketService.delete(id);
        return responseModel;
    }
}
