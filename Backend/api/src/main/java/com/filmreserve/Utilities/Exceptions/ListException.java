package com.filmreserve.Utilities.Exceptions;

/**
 *  Clase para el manejo  de excepciones en listas dinamicas
 * 
 *  @author javiersolanop
 */
public class ListException extends Exception {
    
    // Properties of object:
    private String atrMessage;
    
    // Properties of class:
    public static final int EMPTY = 1;
    public static final int POSITION_OUT_OF_RANGE = 2;
    public static final int POSITION_NEGATIVE = 3;
    
    // Constructors:
    
    public ListException()
    {};

    public ListException(int prmTypeException)
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
            case EMPTY: atrMessage = "La lista esta vacia!"; break;
            case POSITION_OUT_OF_RANGE: atrMessage = "La posicion se encuentra fuera del rango de la lista!"; break;
            case POSITION_NEGATIVE: atrMessage = "La posicion no puede ser negativa!"; break;
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
     * @throws Libraries.Exceptions.ListException Con el tipo de excepcion 
    */
    public static void throwException(boolean prmCondition, int prmTypeException) throws ListException
    {
        if(prmCondition) throw new ListException(prmTypeException);
    }
}
