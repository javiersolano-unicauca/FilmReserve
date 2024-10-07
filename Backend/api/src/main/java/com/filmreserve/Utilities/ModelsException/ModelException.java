package com.filmreserve.Utilities.ModelsException;

/**
 *  Clase para el manejo de excepciones en los modelos
 *  
 *  @author javiersolanop
 */
public abstract class ModelException extends Exception {

    // Properties of object:
    private String atrField;
    private String atrException;
    
    // Constructors:
    public ModelException()
    {}

    public ModelException(int prmField, Exception prmException)
    {
        atrField = getFieldString(prmField);
        atrException = prmException.getMessage();
    }

    @Override
    public String getMessage() 
    {
        return "El campo '" + atrField + "': " + atrException;
    }

    /**
     *  Metodo para obtener la cadena del campo
     * 
     *  @param prmField Recibe el campo
     */
    protected abstract String getFieldString(int prmField);
}
