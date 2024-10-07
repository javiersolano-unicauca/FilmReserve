package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.CustomerModel;

/**
 *  Clase para el manejo de excepciones de la clase 'CustomerModel'
 *  @see CustomerModel
 * 
 *  @author javiersolanop
 */
public class CustomerException  extends UserException {

    // Properties of class:
    public static final int DAY = 7;
    public static final int MONTH = 8;
    public static final int YEAR = 9;
    public static final int PHONE = 10;
    
    // Constructors:
    
    public CustomerException()
    {}

    public CustomerException(int prmField, Exception prmException)
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
            case DAY: varField = "day"; break;
            case MONTH: varField = "month"; break;
            case YEAR: varField = "year"; break;
            case PHONE: varField = "phone"; break;
        }
        return varField;
    }
    
    public static void throwException(int prmField, Exception prmException) throws CustomerException
    {
        throw new CustomerException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws CustomerException
    {
        if(prmCondition) throw new CustomerException(prmField, prmException);
    }
}
