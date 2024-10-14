package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.CinemaRoomModel;

/**
 *  Clase para el manejo de excepciones de la clase 'CinemaRoomModel'
 *  @see CinemaRoomModel
 * 
 *  @author javiersolanop
 */
public class CinemaRoomException extends ModelException {

    // Properties of class:
    public static final int ID_CINEMA_ROOM = 1;

    // Constructors:

    public CinemaRoomException()
    {}

    public CinemaRoomException(int prmField, Exception prmException)
    {
        super(prmField, prmException);
    }

    // Methods:

    @Override
    protected String getFieldString(int prmField) {
        
        String varField = "";

        switch (prmField)
        { 
            case ID_CINEMA_ROOM: varField = "idCinemaRoom"; break;
        }
        return varField;
    }

    public static void throwException(int prmField, Exception prmException) throws CinemaRoomException
    {
        throw new CinemaRoomException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws CinemaRoomException
    {
        if(prmCondition) throw new CinemaRoomException(prmField, prmException);
    }
}
