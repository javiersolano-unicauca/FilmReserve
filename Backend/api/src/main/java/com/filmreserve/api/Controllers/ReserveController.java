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
import com.filmreserve.api.Services.iReserveService;

/**
 *  @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/${api.version}/function-reserve")
public class ReserveController {

    @Autowired
    private iReserveService reserveService;

    @GetMapping(path = "/{idMovie}/{startDate}/{startTime}/{cinemaRoom}")
    public ResponseEntity<String> getFunction(
        @PathVariable("idMovie") Long prmIdMovie, 
        @PathVariable("startDate") LocalDate prmStartDate, 
        @PathVariable("startTime") LocalTime prmStartTime,
        @PathVariable("cinemaRoom") Integer prmCinemaRoom
    )
    {
        try{
            JSON objResponse = reserveService.getFunction(prmIdMovie, prmStartDate, prmStartTime, prmCinemaRoom);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/seats-of-cinemaroom/{idMovie}/{startDate}/{startTime}/{cinemaRoom}")
    public ResponseEntity<Object> getSeatsOfCinemaRoom(
        @PathVariable("idMovie") Long prmIdMovie, 
        @PathVariable("startDate") LocalDate prmStartDate, 
        @PathVariable("startTime") LocalTime prmStartTime,
        @PathVariable("cinemaRoom") Integer prmCinemaRoom
    )
    {
        try{
            return new ResponseEntity<>(reserveService.getSeatsOfCinemaRoom(prmIdMovie, prmStartDate, prmStartTime, prmCinemaRoom).toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/seats-of-function/{idMovie}/{startDate}/{startTime}")
    public ResponseEntity<Object> getSeatsOfFunction(
        @PathVariable("idMovie") Long prmIdMovie, 
        @PathVariable("startDate") LocalDate prmStartDate, 
        @PathVariable("startTime") LocalTime prmStartTime
    )
    {
        try{
            return new ResponseEntity<>(reserveService.getSeatsOfFunction(prmIdMovie, prmStartDate, prmStartTime).toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> save(
        Long idMovie, 
        LocalDate startDate, 
        LocalTime startTime, 
        LocalTime endTime,
        Integer cinemaRoom
    )
    {
        try{
            JSON objResponse = reserveService.save(idMovie, startDate, startTime, endTime, cinemaRoom);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{idMovie}/{startDate}/{startTime}/{cinemaRoom}")
    public ResponseEntity<String> delete(
        @PathVariable("idMovie") Long prmIdMovie, 
        @PathVariable("startDate") LocalDate prmStartDate, 
        @PathVariable("startTime") LocalTime prmStartTime,
        @PathVariable("cinemaRoom") Integer prmCinemaRoom
    )
    {
        try{
            JSON objResponse = reserveService.delete(prmIdMovie, prmStartDate, prmStartTime, prmCinemaRoom);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
