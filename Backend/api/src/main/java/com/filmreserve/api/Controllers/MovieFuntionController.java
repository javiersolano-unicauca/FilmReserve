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
import com.filmreserve.Utilities.Arrays.Lists.LinkedList;
import com.filmreserve.api.Dao.iMovieFunctionDao;
import com.filmreserve.api.Models.MovieFunctionModel;
import com.filmreserve.api.Models.MovieFunctionPK;
import com.filmreserve.api.Services.iMovieFunctionService;

import java.time.LocalDate;
import java.util.List;

/**
 *  Clase controladora para la gestion de funciones
 * 
 *  @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/v2/movie-function")
public class MovieFuntionController {

    @Autowired
    iMovieFunctionService movieFunctionService;

    @GetMapping(path = "/{idMovie}/{idCinemaRoom}/{startDate}")
    public ResponseEntity<String> get(
        @PathVariable("idMovie") Long prmIdMovie, 
        @PathVariable("idCinemaRoom") Long prmCinemaRoom,
        @PathVariable("startDate") LocalDate prmStartDate
    ){
        try{
            JSON objResponse = movieFunctionService.getMovieFunction(prmIdMovie, prmCinemaRoom, prmStartDate);
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
            return new ResponseEntity<>(movieFunctionService.all(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> save(MovieFunctionPK prMovieFunctionPK, MovieFunctionModel prmMovieFunction)
    {
        try{
            JSON objResponse = movieFunctionService.save(prMovieFunctionPK, prmMovieFunction);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{idMovie}/{idCinemaRoom}/{startDate}")
    public ResponseEntity<String> delete(
        @PathVariable("idMovie") Long prmIdMovie, 
        @PathVariable("idCinemaRoom") Long prmCinemaRoom,
        @PathVariable("startDate") LocalDate prmStartDate
    ){
        try{
            JSON objResponse = movieFunctionService.delete(prmIdMovie, prmCinemaRoom, prmStartDate);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
