package com.filmreserve.api.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Services.iSellService;

/**
 *  @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/${api.version}/sell-ticket")
public class SellController {

    @Autowired
    iSellService sellService;

    @GetMapping(path = "/{identification}/{idMovie}/{cinemaRoom}/{startDate}/{startTime}")
    public ResponseEntity<String> getPurchasedSeatsAndPurchase(
        @PathVariable("identification") Long prmIdentification,
        @PathVariable("idMovie") Long prmIdMovie,
        @PathVariable("cinemaRoom") Integer prmCinemaRoom,
        @PathVariable("startDate") LocalDate prmStartDate,
        @PathVariable("startTime") LocalTime prmStartTime
    ){
        try{
            JSON objResponse = sellService.getSelledSeatsAndSell(
                prmIdentification,
                prmIdMovie,
                prmCinemaRoom,
                prmStartDate,
                prmStartTime
            );
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> save(
        Long identification,
        Long idMovie,
        Integer cinemaRoom,
        LocalDate startDate,
        LocalTime startTime,
        String listSelledSeats
    )
    {
        try{
            JSON objResponse = sellService.save(identification, idMovie, cinemaRoom, startDate, startTime, listSelledSeats);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{identification}/{idMovie}/{cinemaRoom}/{startDate}/{startTime}")
    public ResponseEntity<String> delete(
        @PathVariable("identification") Long prmIdentification,
        @PathVariable("idMovie") Long prmIdMovie,
        @PathVariable("cinemaRoom") Integer prmCinemaRoom,
        @PathVariable("startDate") LocalDate prmStartDate,
        @PathVariable("startTime") LocalTime prmStartTime
    ){
        try{
            JSON objResponse = sellService.delete(prmIdentification, prmIdMovie, prmCinemaRoom, prmStartDate, prmStartTime);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
