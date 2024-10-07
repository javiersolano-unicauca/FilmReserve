package com.filmreserve.Utilities.Exceptions;

/**
*  @author javiersolanop
*/
public class MenuException extends Exception {

    // Properties of object:
    private String atrMessage;

    // Properties of class:
    public static final int INVALID_OPTION = 1;

    // Constructors:
    public MenuException(int prmTypeException)
    {
        generateMessage(prmTypeException);
    }

    // Methods:
    private void generateMessage(int prmTypeException)
    {
        switch(prmTypeException){

            case INVALID_OPTION: atrMessage = "Opcion no valida!"; break;
        }
    }

    @Override
    public String getMessage()
    {
        return atrMessage;
    }
}
