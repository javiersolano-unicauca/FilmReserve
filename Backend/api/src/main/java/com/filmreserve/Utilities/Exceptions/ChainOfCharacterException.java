package com.filmreserve.Utilities.Exceptions;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;

/**
 *  Clase para el manejo de excepciones de la clase 'ChainOfCharacter'
 *  @see ChainOfCharacter
 * 
 *  @author javiersolanop
 */
public class ChainOfCharacterException extends Exception {

    // Properties of object:
    private String atrMessage;

    // Properties of class:
    public static final int NOT_CONTAINS_LETTERS = 1;
    public static final int NOT_NUMBER_INTEGER = 2;
    public static final int NOT_NUMBER_REAL = 3;
    public static final int NOT_PATTERN = 4;
    
    // Constructors:
    
    public ChainOfCharacterException()
    {};

    public ChainOfCharacterException(int prmTypeException)
    {
        generateMessage(prmTypeException);
    };
    
    /**
     *  Metodo para generar la descripcion del error, 
     *  dependiendo del tipo de excepcion
     * 
     *  @param prmTypeException Recibe el tipo de excepcion establecida en la clase
     */
    private void generateMessage(int prmTypeException)
    {
        switch (prmTypeException)
        {
            case NOT_CONTAINS_LETTERS: atrMessage = "Debe contener solamente letras!"; break;
            case NOT_NUMBER_INTEGER: atrMessage = "Debe ser un numero entero!"; break;
            case NOT_NUMBER_REAL: atrMessage = "Debe ser un numero real!"; break;
            case NOT_PATTERN: atrMessage = "No contiene un patron permitido!"; break;
        }
    }
    
    @Override
    public String getMessage()
    {
        return atrMessage;
    }
    
    /**
     *  Metodo para lanzar una excepcion con base en la condicion recibida;
     * 
     *  @param prmCondition Recibe la condicion
     *  @param prmTypeException Recibe el tipo de excepcion a lazar
     * 
     * @throws Libraries.Exceptions.ChainOfCharacterException Con el tipo de excepcion 
    */
    public static void throwException(boolean prmCondition, int prmTypeException) throws ChainOfCharacterException
    {
        if(prmCondition) throw new ChainOfCharacterException(prmTypeException);
    }
}
