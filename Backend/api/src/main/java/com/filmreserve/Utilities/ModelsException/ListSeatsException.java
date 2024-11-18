package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.ReserveModel;

/**
 *  Clase para el manejo de excepciones de las clase  'ReserveModel'
 *  @see ReserveModel
 * 
 *  @author javiersolanop
 */
public class ListSeatsException extends ModelException {
 
    // Properties of class:
    public static final int LIST_SEATS = 1;

    // Constructors:
    
    public ListSeatsException()
    {}

    public ListSeatsException(int prmField, Exception prmException)
    {
        super(prmField, prmException);
    }

    // Methods:
    
    @Override
    protected String getFieldString(int prmField) {
        
        String varField = "";

        switch (prmField)
        { 
            case LIST_SEATS: varField = "listSeats"; break;
        }
        return varField;
    }
    
    public static void throwException(int prmField, Exception prmException) throws ListSeatsException
    {
        throw new ListSeatsException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws ListSeatsException
    {
        if(prmCondition) throw new ListSeatsException(prmField, prmException);
    }
}
