package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.Utilities.Arrays.JSON.JSON;

/**
 *  Clase para el manejo de las respuestas de los servicios
 * 
 *  @author javiersolanop
 */
public class ServiceResponseException extends Exception {

    // Properties of object:
    private JSON atrJson;

    // Constructor:

    ServiceResponseException(String prmResponse, String prmCause)
    {
        try{
            atrJson = new JSON();
            atrJson.add(prmResponse, false);
            atrJson.add("cause", prmCause);
        }
        catch(Exception e){}
    }

    @Override
    public String getMessage() {
        return atrJson.toString();
    }

    /**
     *  @param prmResponse Recibe el tipo de respuesta
     *  @param prmCause Recibe la causa de la excepcion
     */
    public static void throwException(String prmResponse, String prmCause) throws ServiceResponseException
    {
        throw new ServiceResponseException(prmResponse, prmCause);
    }

    /**
     *  @param prmResponse Recibe el tipo de respuesta
     *  @param prmCause Recibe la causa de la excepcion
     */
    public static void throwException(Boolean prmCondition, String prmResponse, String prmCause) throws ServiceResponseException
    {
        if(prmCondition) throw new ServiceResponseException(prmResponse, prmCause);
    }
}
