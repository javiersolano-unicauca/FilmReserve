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
import com.filmreserve.api.Models.CinemaRoomModel;
import com.filmreserve.api.Services.iCinemaRoomService;

@RestController
@RequestMapping(path = "/api/v2/cinema-room")
public class CinemaRoomController {

    @Autowired
    iCinemaRoomService cinemaRoomService;

    @GetMapping(path = "/{idCinemaRoom}")
    public ResponseEntity<String> get(@PathVariable("idCinemaRoom") Long prmIdCinemaRoom)
    {
        try{
            JSON objResponse = cinemaRoomService.getCinemaRoom(prmIdCinemaRoom);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> save(CinemaRoomModel prmCinemaRoom)
    {
        try{
            JSON objResponse = cinemaRoomService.save(prmCinemaRoom);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{idCinemaRoom}")
    public ResponseEntity<String> delete(@PathVariable("idCinemaRoom") Long prmIdCinemaRoom)
    {
        try{
            JSON objResponse = cinemaRoomService.delete(prmIdCinemaRoom);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
