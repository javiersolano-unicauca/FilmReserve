package com.filmreserve.api.Controllers;

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
import com.filmreserve.api.Models.SeatPK;
import com.filmreserve.api.Services.iSeatService;

@RestController
@RequestMapping(path = "/api/v2/seat")
public class SeatController {

    @Autowired
    iSeatService seatService;

    @GetMapping(path = "/{cinemaRoom}")
    public ResponseEntity<String> getSeats(@PathVariable("cinemaRoom") Integer prmCinemaRoom)
    {
        try{
            return new ResponseEntity<>(seatService.getSeatsOfCinemaRoom(prmCinemaRoom).toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> save(SeatPK prmSeatPK)
    {
        try{
            JSON objResponse = seatService.save(prmSeatPK);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{cinemaRoom}/{row}/{numColumn}")
    public ResponseEntity<String> delete(
        @PathVariable("cinemaRoom") Integer prmCinemaRoom,
        @PathVariable("row") Character prmRow,
        @PathVariable("numColumn") Integer prmNumColumn
    ){
        try{
            JSON objResponse = seatService.delete(prmCinemaRoom, prmRow, prmNumColumn);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
