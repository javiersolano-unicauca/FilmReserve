package com.filmreserve.Utilities.Validations;

import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.ModelsException.MovieFunctionException;
import com.filmreserve.api.Models.MovieFunctionModel;
import com.filmreserve.api.Models.MovieFunctionPK;

/**
 *  Clase para las validaciones de las funciones
 *  @see MovieFunctionModel
 * 
 *  @author javiersolanop
 */
public class MovieFunctionValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmMovieFunctionPK Recibe la referencia del id
     *  @param prmMovieFunction Recibe la referencia de la funcion
     * 
     *  @throws MovieFunctionException Cuando los campos no son validos en su totalidad
     */
    public static void validate(MovieFunctionPK prmMovieFunctionPK, MovieFunctionModel prmMovieFunction) throws MovieFunctionException
    {
        validateId(prmMovieFunctionPK);
        validateEndDate(prmMovieFunctionPK, prmMovieFunction);
        validateStartTime(prmMovieFunction);
        validateEndTime(prmMovieFunction);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws MovieFunctionException Si el campo esta vacio
     */
    private static void validateNull(Object prmField, int prmFieldType) throws MovieFunctionException
    {
        MovieFunctionException.throwException(
            prmField == null, 
            prmFieldType,
            new Exception("No debe estar nulo")
        );
    }

    /**
     *  @param prmMovieFunctionPK Recibe la referencia del id
     * 
     *  @throws MovieFunctionException Si el campo no es valido
     */
    public static void validateId(MovieFunctionPK prmMovieFunctionPK) throws MovieFunctionException 
    {   
        validateNull(prmMovieFunctionPK.getIdMovie(), MovieFunctionException.ID_MOVIE);
        validateNull(prmMovieFunctionPK.getCinemaRoom(), MovieFunctionException.ID_CINEMA_ROOM);
        validateNull(prmMovieFunctionPK.getStartDate(), MovieFunctionException.START_DATE);

        MovieFunctionException.throwException(
            (prmMovieFunctionPK.getIdMovie() <= 0), 
            MovieFunctionException.ID_MOVIE,
            new Exception("Debe ser positivo")
        );

        MovieFunctionException.throwException(
            (prmMovieFunctionPK.getCinemaRoom() <= 0), 
            MovieFunctionException.ID_CINEMA_ROOM,
            new Exception("Debe ser positivo")
        );

        LocalDate varNowDate = LocalDate.now();

        MovieFunctionException.throwException(
            (varNowDate.isAfter(prmMovieFunctionPK.getStartDate())),
            MovieFunctionException.START_DATE,
            new Exception("Debe ser superior a la fecha actual")
        );
    }
    
    /**
     *  @param prmMovieFunctionPK Recibe la referencia del id
     *  @param prmMovieFunction Recibe la referencia a la funcion
     * 
     *  @throws MovieFunctionException Si el campo no es valido
     */
    public static void validateEndDate(MovieFunctionPK prmMovieFunctionPK, MovieFunctionModel prmMovieFunction) throws MovieFunctionException
    {
        validateNull(prmMovieFunction.getEndDate(), MovieFunctionException.END_DATE);
        LocalDate varNowDate = LocalDate.now();

        MovieFunctionException.throwException(
            (varNowDate.isAfter(prmMovieFunction.getEndDate())),
            MovieFunctionException.END_DATE,
            new Exception("Debe ser superior a la fecha actual")
        );

        MovieFunctionException.throwException(
            (prmMovieFunctionPK.getStartDate().isAfter(prmMovieFunction.getEndDate())),
            MovieFunctionException.END_DATE,
            new Exception("Debe ser superior a la fecha de inicio")
        );
    }

    /**
     *  @param prmMovieFunction Recibe la referencia a la funcion
     * 
     *  @throws MovieFunctionException Si el campo no es valido
     */
    public static void validateStartTime(MovieFunctionModel prmMovieFunction) throws MovieFunctionException
    {
        validateNull(prmMovieFunction.getStartTime(), MovieFunctionException.START_TIME);
        String varMin = "14:00", varMax = "20:00";

        MovieFunctionException.throwException(
            (prmMovieFunction.getStartTime().isBefore(LocalTime.parse(varMin))
            || prmMovieFunction.getStartTime().isAfter(LocalTime.parse(varMax))),
            MovieFunctionException.START_TIME,
            new Exception("Debe ser estar entre las " + varMin + " y " + varMax + " horas")
        );
    }

    /**
     *  @param prmMovieFunction Recibe la referencia a la funcion
     * 
     *  @throws MovieFunctionException Si el campo no es valido
     */
    public static void validateEndTime(MovieFunctionModel prmMovieFunction) throws MovieFunctionException
    {
        validateNull(prmMovieFunction.getEndTime(), MovieFunctionException.END_TIME);
        String varMin = "15:30", varMax = "22:00";

        MovieFunctionException.throwException(
            (prmMovieFunction.getEndTime().isBefore(LocalTime.parse(varMin))
            || prmMovieFunction.getEndTime().isAfter(LocalTime.parse(varMax))),
            MovieFunctionException.END_TIME,
            new Exception("Debe ser estar entre las " + varMin + " y " + varMax + " horas")
        );

        MovieFunctionException.throwException(
            (prmMovieFunction.getStartTime().isAfter(prmMovieFunction.getEndTime())),
            MovieFunctionException.END_TIME,
            new Exception("Debe ser superior a la hora de inicio")
        );
    }
}
