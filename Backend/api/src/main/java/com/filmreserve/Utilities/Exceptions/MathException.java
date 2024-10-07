 package com.filmreserve.Utilities.Exceptions;

/**
*  @author javiersolanop
*/
public class MathException extends Exception {

    // Properties of object:
    private String atrMessage;

    // Properties of class:
    public static final int DIVISION = 1;
    public static final int NOT_INTEGER = 2;
    public static final int NOT_DOUBLE = 3;
    public static final int INDETERMINATE = 4;
    public static final int POSITIVE_INTEGER = 5;

    // Constructors:
    public MathException(int prmTypeException)
    {
        generateMessage(prmTypeException);
    }

    // Methods:
    private void generateMessage(int prmTypeException)
    {
        switch(prmTypeException){

            case DIVISION: atrMessage = "La division entre cero no esta definida!"; break;
            case NOT_INTEGER: atrMessage = "El dato ingresado no es un numero entero!"; break;
            case NOT_DOUBLE: atrMessage = "El dato ingresado no es un numero real!"; break;
            case INDETERMINATE: atrMessage = "Indeterminado!"; break;
            case POSITIVE_INTEGER: atrMessage = "El dato ingresado no es un numero entero positivo!"; break;
        }
    }

    @Override
    public String getMessage()
    {
        return atrMessage;
    }
}
