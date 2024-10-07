package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.UserModel;

/**
 *  Clase para el manejo de excepciones de la clase 'UserModel'
 *  @see UserModel
 * 
 *  @author javiersolanop
 */
public class UserException extends ModelException {

    // Properties of class:
    public static final int IDENTIFICATION = 1;
    public static final int FIRST_NAME = 2;
    public static final int SECOND_NAME = 3;
    public static final int FIRST_SURNAME = 4;
    public static final int SECOND_SURNAME = 5;
    public static final int PASSWORD = 6;
    
    // Constructors:
    
    public UserException()
    {}

    public UserException(int prmField, Exception prmException)
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
        }
        return varField;
    }
    
    public static void throwException(int prmField, Exception prmException) throws UserException
    {
        throw new UserException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws UserException
    {
        if(prmCondition) throw new UserException(prmField, prmException);
    }
}
