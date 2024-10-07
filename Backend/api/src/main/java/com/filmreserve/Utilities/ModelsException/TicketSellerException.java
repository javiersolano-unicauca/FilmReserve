package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.TicketSellerModel;

/**
 *  Clase para el manejo de excepciones de la clase 'TicketSellerModel'
 *  @see TicketSellerModel
 * 
 *  @author javiersolanop
 */
public class TicketSellerException extends UserException {

    // Properties of class:
    public static final int TURN = 7;
    
    // Constructors:
    
    public TicketSellerException()
    {}

    public TicketSellerException(int prmField, Exception prmException)
    {
        super(prmField, prmException);
    }

    // Methods:
    
    @Override
    protected String getFieldString(int prmField) {
        
        String varField = "";

        switch (prmField)
        { 
            case IDENTIFICATION: varField = "identification"; break;
            case FIRST_NAME: varField = "firstName"; break;
            case SECOND_NAME: varField = "secondName"; break;
            case FIRST_SURNAME: varField = "firstSurname"; break;
            case SECOND_SURNAME: varField = "secondSurname"; break;
            case PASSWORD: varField = "password"; break;
            case TURN: varField = "turn"; break;
        }
        return varField;
    }
    
    public static void throwException(int prmField, Exception prmException) throws TicketSellerException
    {
        throw new TicketSellerException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws TicketSellerException
    {
        if(prmCondition) throw new TicketSellerException(prmField, prmException);
    }
}
