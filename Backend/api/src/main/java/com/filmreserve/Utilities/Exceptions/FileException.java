package com.filmreserve.Utilities.Exceptions;

import com.filmreserve.Utilities.Files.File;

/**
 *  Clase para el manejo de excepciones de archivos
 *  @see File
 * 
 *  @author javiersolanop
 */
public class FileException extends Exception {

    // Properties of object:
    private String atrMessage;

    // Properties of class:
    public static final int CANNOT_IMPORT = 1;
    public static final int CANNOT_EXPORT = 2;
    public static final int CANNOT_DELETE = 3;
    
    // Constructors:
    
    public FileException()
    {};

    public FileException(int prmTypeException)
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
            case CANNOT_IMPORT: atrMessage = "No se puede importar!"; break;
            case CANNOT_EXPORT: atrMessage = "No se puede exportar!"; break;
            case CANNOT_DELETE: atrMessage = "No se puede eliminar!"; break;
        }
    }
    
    @Override
    public String getMessage()
    {
        return "Archivo: " + atrMessage;
    }

    /**
     *  Metodo para lanzar una excepcion;
     * 
     *  @param prmCondition Recibe la condicion
     *  @param prmTypeException Recibe el tipo de excepcion a lazar
     * 
     * @throws Libraries.Exceptions.FileException Con el tipo de excepcion 
    */
    public static void throwException(int prmTypeException) throws FileException
    {
        throw new FileException(prmTypeException);
    }
    
    /**
     *  Metodo para lanzar una excepcion con base en la condicion recibida;
     * 
     *  @param prmCondition Recibe la condicion
     *  @param prmTypeException Recibe el tipo de excepcion a lazar
     * 
     * @throws Libraries.Exceptions.FileException Con el tipo de excepcion 
    */
    public static void throwException(boolean prmCondition, int prmTypeException) throws FileException
    {
        if(prmCondition) throw new FileException(prmTypeException);
    }
}
