package com.filmreserve.api.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.MovieFunctionValidation;
import com.filmreserve.api.Dao.iMovieFunctionDao;
import com.filmreserve.api.Models.MovieFunctionModel;
import com.filmreserve.api.Models.MovieFunctionPK;
import com.filmreserve.api.Services.iMovieFunctionService;

import java.time.LocalDate;
import java.util.List;

/**
 *  @author javiersolanop
 */
@Service
public class MovieFunctionServiceImp implements iMovieFunctionService {

    @Autowired
    iMovieFunctionDao movieFunctionDao;

    @Override
    public MovieFunctionModel getMovieFunctionModel(Long prmIdMovie, Long prmIdCinemaRoom, LocalDate prmStartDate) throws Exception 
    {
        return movieFunctionDao.findById(new MovieFunctionPK(prmIdMovie, prmIdCinemaRoom, prmStartDate))
               .orElse(null);
    }

    @Override
    public JSON getMovieFunction(Long prmIdMovie, Long prmIdCinemaRoom, LocalDate prmStartDate) throws Exception 
    {
        MovieFunctionModel objMovieFunction = getMovieFunctionModel(prmIdMovie, prmIdCinemaRoom, prmStartDate);

        ServiceResponseException.throwException(
            (objMovieFunction == null),
            "getMovieFunction",
            "No existe la funcion con la identificacion de pelicula " + 
            + prmIdMovie + ", sala " + prmIdCinemaRoom + " y fecha de inicio " + prmStartDate.toString()    
        );

        JSON objResponse = new JSON();
        objResponse.add("getMovieFunction", true);
        objResponse.add("movieFunction", objMovieFunction.toJSON());
        return objResponse;
    }

    @Override
    public List<MovieFunctionModel> all() throws Exception {
        
        List<MovieFunctionModel> objList = (List<MovieFunctionModel>) movieFunctionDao.findAll();
        
        ServiceResponseException.throwException(
            objList.isEmpty(),
            "getMovieFunctions",
            "No existen funciones en el sistema"
        );  

        return objList;
    }

    @Override
    public JSON save(MovieFunctionPK prmMovieFunctionPK, MovieFunctionModel prmMovieFunction) throws Exception 
    {
        ServiceResponseException.throwException(
            movieFunctionDao.existsById(prmMovieFunctionPK),
            "save",
            "Ya existe esa pelicula en el sistema"
        );

        try{ MovieFunctionValidation.validate(prmMovieFunctionPK, prmMovieFunction); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "save", 
                e.getMessage()
        ); }
        
        prmMovieFunction.setIdMovieFunction(prmMovieFunctionPK);
        prmMovieFunction.setCinemaRoom(prmMovieFunctionPK.getIdCinemaRoom());
        prmMovieFunction.setMovie(prmMovieFunctionPK.getIdMovie());
        prmMovieFunction.setActive(true);
        movieFunctionDao.save(prmMovieFunction);
        
        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON functionEnd(MovieFunctionPK prmMovieFunctionPK) throws Exception {
        
        MovieFunctionModel objMovieFunction = getMovieFunctionModel(
            prmMovieFunctionPK.getIdMovie(),
            prmMovieFunctionPK.getIdCinemaRoom(),
            prmMovieFunctionPK.getStartDate()
        );

        ServiceResponseException.throwException(
            (objMovieFunction == null),
            "functionEnd",
            "No existe la funcion con la identificacion de pelicula " + 
            + prmMovieFunctionPK.getIdMovie() + ", sala " +
            + prmMovieFunctionPK.getIdCinemaRoom() + " y fecha de inicio " + prmMovieFunctionPK.getStartDate().toString()    
        );

        objMovieFunction.setActive(false);
        movieFunctionDao.save(objMovieFunction);

        JSON objResponse = new JSON();
        objResponse.add("functionEnd", true);
        return objResponse;
    }

    @Override
    public JSON delete(Long prmIdMovie, Long prmIdCinemaRoom, LocalDate prmStartDate) throws Exception 
    {
        MovieFunctionPK objId = new MovieFunctionPK(prmIdMovie, prmIdCinemaRoom, prmStartDate);

        MovieFunctionModel objMovieFunction = movieFunctionDao.findById(objId).orElse(null);

        ServiceResponseException.throwException(
            (objMovieFunction == null),
            "delete",
            "No existe la funcion con la identificacion de pelicula " + 
            + prmIdMovie + ", sala " + prmIdCinemaRoom + " y fecha de inicio " + prmStartDate.toString()
        );

        movieFunctionDao.deleteById(objId);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }
}
