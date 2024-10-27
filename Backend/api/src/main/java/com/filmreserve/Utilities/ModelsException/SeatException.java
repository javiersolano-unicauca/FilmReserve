package com.filmreserve.Utilities.ModelsException;

import com.filmreserve.api.Models.SeatModel;

/**
 *  Clase para el manejo de excepciones de la clase 'CinemaRoomModel'
 *  @see SeatModel
 * 
 *  @author javiersolanop
 */
public class SeatException extends ModelException {

    // Properties of class:
    public static final int CINEMA_ROOM = 1;
    public static final int ROW = 2;
    public static final int NUM_COLUMN = 3;

    // Constructors:

    public SeatException()
    {}

    public SeatException(int prmField, Exception prmException)
    {
        super(prmField, prmException);
    }

    // Methods:

    @Override
    protected String getFieldString(int prmField) {
        
        String varField = "";

        switch (prmField)
        { 
            case CINEMA_ROOM: varField = "cinemaRoom"; break;
            case ROW: varField = "row"; break;
            case NUM_COLUMN: varField = "column"; break;
        }
        return varField;
    }

    public static void throwException(int prmField, Exception prmException) throws SeatException
    {
        throw new SeatException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws SeatException
    {
        if(prmCondition) throw new SeatException(prmField, prmException);
    }
}
