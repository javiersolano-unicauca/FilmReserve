package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.MovieFunctionModel;

/**
 *  Clase para el manejo de excepciones de la clase 'MovieFunctionModel'
 *  @see MovieFunctionModel
 * 
 *  @author javiersolanop
 */
public class MovieFunctionException extends ModelException {

    // Properties of class:
    public static final int ID_MOVIE = 1;
    public static final int ID_CINEMA_ROOM = 2;
    public static final int START_DATE = 3;
    public static final int END_DATE = 4;
    public static final int START_TIME = 5;
    public static final int END_TIME = 6;
    
    // Constructors:
    
    public MovieFunctionException()
    {}

    public MovieFunctionException(int prmField, Exception prmException)
    {
        super(prmField, prmException);
    }

    // Methods:
    
    @Override
    protected String getFieldString(int prmField) {
        
        String varField = "";

        switch (prmField)
        { 
            case ID_MOVIE: varField = "idMovie"; break;
            case ID_CINEMA_ROOM: varField = "idCinemaRoom"; break;
            case START_DATE: varField = "startDate"; break;
            case END_DATE: varField = "endDate"; break;
            case START_TIME: varField = "startTime"; break;
            case END_TIME: varField = "endTime"; break;
        }
        return varField;
    }
    
    public static void throwException(int prmField, Exception prmException) throws MovieFunctionException
    {
        throw new MovieFunctionException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws MovieFunctionException
    {
        if(prmCondition) throw new MovieFunctionException(prmField, prmException);
    }
}
