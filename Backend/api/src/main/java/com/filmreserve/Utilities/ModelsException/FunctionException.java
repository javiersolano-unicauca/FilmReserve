package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.FunctionModel;

/**
 *  Clase para el manejo de excepciones de la clase 'MovieFunctionModel'
 *  @see FunctionModel
 * 
 *  @author javiersolanop
 */
public class FunctionException extends ModelException {

    // Properties of class:
    public static final int ID_MOVIE = 1;
    public static final int START_DATE = 2;
    public static final int START_TIME = 3;
    public static final int END_TIME = 4;
    
    // Constructors:
    
    public FunctionException()
    {}

    public FunctionException(int prmField, Exception prmException)
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
            case START_DATE: varField = "startDate"; break;
            case START_TIME: varField = "startTime"; break;
            case END_TIME: varField = "endTime"; break;
        }
        return varField;
    }
    
    public static void throwException(int prmField, Exception prmException) throws FunctionException
    {
        throw new FunctionException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws FunctionException
    {
        if(prmCondition) throw new FunctionException(prmField, prmException);
    }
}
