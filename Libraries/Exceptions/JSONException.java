package com.filmreserve.Libraries.Exceptions;

import com.filmreserve.Libraries.Arrays.JSON.JSON;

/**
 *  Clase para el manejo  de excepciones en objetos JSON
 *  @see JSON
 * 
 *  @author javiersolanop
 */
public class JSONException extends Exception {
    
    // Properties of object:
    private String atrMessage;
    
    // Properties of class:
    public static final int KEY_DUPLICATED = 1;
    public static final int NOT_DATA_VALID = 2;
    
    // Constructors:
    
    public JSONException()
    {};

    public JSONException(int prmTypeException)
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
            case KEY_DUPLICATED: atrMessage = "Ya existe esa clave!"; break;
            case NOT_DATA_VALID: atrMessage = "El tipo de dato no es valido!"; break;
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
     * @throws Libraries.Exceptions.JSONException Con el tipo de excepcion 
    */
    public static void throwException(boolean prmCondition, int prmTypeException) throws JSONException
    {
        if(prmCondition) throw new JSONException(prmTypeException);
    }
}
