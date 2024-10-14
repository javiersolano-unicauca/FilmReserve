package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.MovieModel;

/**
 *  Clase para el manejo de excepciones de la clase 'MovieModel'
 *  @see MovieModel
 * 
 *  @author javiersolanop
 */
public class MovieException extends ModelException {

    // Properties of class:
    public static final int ID_MOVIE = 1;
    public static final int NAME = 2;
    public static final int SYPNOSIS = 3;
    public static final int POSTER_IMAGE = 4;
    public static final int GENDERS = 5;
    
    // Constructors:
    
    public MovieException()
    {}

    public MovieException(int prmField, Exception prmException)
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
            case NAME: varField = "name"; break;
            case SYPNOSIS: varField = "sypnosis"; break;
            case POSTER_IMAGE: varField = "poster"; break;
            case GENDERS: varField = "genders"; break;
        }
        return varField;
    }
    
    public static void throwException(int prmField, Exception prmException) throws MovieException
    {
        throw new MovieException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws MovieException
    {
        if(prmCondition) throw new MovieException(prmField, prmException);
    }
}
