package com.filmreserve.api.Services;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.MovieFunctionModel;
import com.filmreserve.api.Models.MovieFunctionPK;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;

/**
 * @author javiersolanop
 */
public interface iMovieFunctionService {

    /**
     *  Metodo para obtener una funcion por 'MovieFunctionPK'
     *  
     *  @param prmIdMovie Recibe la identificacion de la pelicula
     *  @param prmCinemaRoom Recibe la identificacion de la sala
     *  @param prmStartDate Recibe la fecha de inicio
     *  
     *  @return Una instancia del modelo con la informacion de la funcion si existe.
     *          De lo contrario null
     */
    public MovieFunctionModel getMovieFunctionModel(Long prmIdMovie, Integer prmCinemaRoom, LocalDate prmStartDate) throws Exception;

    /**
     *  Metodo para obtener una pelicula por 'MovieFunctionPK'
     *  
     *  @param prmIdMovie Recibe la identificacion de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio
     *  
     *  @return Una instancia JSON con la clave 'getMovieFunction' en 'true' y con la
     *          informacion de la funcion si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getMovieFunction(Long prmIdMovie, Integer prmCinemaRoom, LocalDate prmStartDate) throws Exception;

    /**
     * Metodo para obtener los asientos de la sala de una funcion
     * 
     * @param prmIdMovie   Recibe el id de la pelicula
     * @param prmStartDate Recibe la fecha de inicio
     * @param prmStartTime Recibe la hora de inicio
     * 
     * @return La lista con los asientos o vacia si no existe la funcion
     */
    public JSON getSeats(Long prmIdMovie, LocalDate prmStartDate, LocalTime prmStartTime) throws Exception;

    /**
     *  Metodo para obtener todas las funciones
     * 
     *  @return La lista con funciones
     * 
     * @throws Exception Si no hay funciones
     */
    public List<MovieFunctionModel> all() throws Exception;

    /**
     *  Metodo para guardar una funcion
     * 
     *  @param prmMovieFunctionPK Recibe el id
     *  @param prmMovieFunction Recibe los datos de la funcion
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede guardar la funcion
     */
    public JSON save(MovieFunctionPK prmMovieFunctionPK, MovieFunctionModel prmMovieFunction) throws Exception;

    /**
     *  Metodo para actualizar el poster de una pelicula
     *  
     *  @param prmIdMovie Recibe la identificacion
     * 
     *  @return Una instancia JSON con la clave 'functionEnd' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede actualizar el poster
     */
    public JSON functionEnd(MovieFunctionPK prmMovieFunctionPK) throws Exception;

    /**
     *  Metodo para eliminar una funcion
     * 
     *  @param prmIdMovie Recibe la identificacion de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio
     * 
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON delete(Long prmIdMovie, Integer prmCinemaRoom, LocalDate prmStartDate) throws Exception;
}
