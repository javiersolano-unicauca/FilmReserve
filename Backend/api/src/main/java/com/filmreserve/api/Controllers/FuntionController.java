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
import com.filmreserve.api.Models.FunctionPK;
import com.filmreserve.api.Services.iFunctionService;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *  Clase controladora para la gestion de funciones
 * 
 *  @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/${api.version}/function")
public class FuntionController {

    @Autowired
    iFunctionService functionService;

    @GetMapping(path = "/{idMovie}/{startDate}/{startTime}")
    public ResponseEntity<String> get(
        @PathVariable("idMovie") Long prmIdMovie, 
        @PathVariable("startDate") LocalDate prmStartDate,
        @PathVariable("startTime") LocalTime prmStartTime
    ){
        try{
            JSON objResponse = functionService.getFunction(prmIdMovie, prmStartDate, prmStartTime);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Object> all() throws Exception
    {
        try{
            return new ResponseEntity<>(functionService.all(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> save(FunctionPK prMovieFunctionPK)
    {
        try{
            JSON objResponse = functionService.save(prMovieFunctionPK);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{idMovie}/{startDate}/{startTime}")
    public ResponseEntity<String> delete(
        @PathVariable("idMovie") Long prmIdMovie, 
        @PathVariable("startDate") LocalDate prmStartDate,
        @PathVariable("startTime") LocalTime prmStartTime
    ){
        try{
            JSON objResponse = functionService.delete(prmIdMovie, prmStartDate, prmStartTime);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
