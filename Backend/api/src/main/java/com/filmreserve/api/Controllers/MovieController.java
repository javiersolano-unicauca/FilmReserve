package com.filmreserve.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.MovieModel;
import com.filmreserve.api.Services.iMovieService;

/**
 *  Clase controladora para la gestion de peliculas
 * 
 *  @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/${api.version}/movie")
public class MovieController {

    @Autowired
    iMovieService movieService;

    @GetMapping(path = "/{idMovie}")
    public ResponseEntity<Object> get(@PathVariable("idMovie") Long prmIdMovie) throws Exception 
    {
        try{
            return new ResponseEntity<>(movieService.getMovieModel(prmIdMovie), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Object> all() throws Exception 
    {
        try{
            return new ResponseEntity<>(movieService.all(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> save(MovieModel prmMovie, String listGenders, MultipartFile poster)
    {
        try{
            JSON objResponse = movieService.save(prmMovie, listGenders, poster);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{idMovie}")
    public ResponseEntity<String> updatePosterImage(@PathVariable("idMovie") Long prmIdMovie, MultipartFile poster)
    {
        try{
            JSON objResponse = movieService.updatePosterImage(prmIdMovie, poster);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{idMovie}")
    public ResponseEntity<String> delete(@PathVariable("idMovie") Long prmIdMovie)
    {
        try{
            JSON objResponse = movieService.delete(prmIdMovie);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
