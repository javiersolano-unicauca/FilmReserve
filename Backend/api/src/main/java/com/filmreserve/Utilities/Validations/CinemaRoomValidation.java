package com.filmreserve.Utilities.Validations;

import com.filmreserve.Utilities.ModelsException.CinemaRoomException;
import com.filmreserve.api.Models.CinemaRoomModel;

/**
 *  Clase para las validaciones de las salas
 *  @see CinemaRoomModel
 * 
 *  @author javiersolanop
 */
public class CinemaRoomValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmCinemaRoom Recibe la referencia de la sala
     * 
     *  @throws CinemaRoomException Cuando los campos no son validos en su totalidad
     */
    public static void validate(CinemaRoomModel prmCinemaRoom) throws CinemaRoomException
    {
        validateId(prmCinemaRoom);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws CinemaRoomException Si el campo esta vacio
     */
    private static void validateNull(Object prmField, int prmFieldType) throws CinemaRoomException
    {
        CinemaRoomException.throwException(
            prmField == null, 
            prmFieldType,
            new Exception("No debe estar nulo")
        );
    }

    /**
     *  Metodo para validar si un campo esta vacio
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws CinemaRoomException Si el campo esta vacio
     */
    private static void validateEmpty(String prmField, int prmFieldType) throws CinemaRoomException
    {
        CinemaRoomException.throwException(
            prmField.isEmpty(), 
            prmFieldType,
            new Exception("No debe estar vacio")
        );
    }

    /**
     *  @param prmCinemaRoom Recibe la referencia de la sala
     * 
     *  @throws CinemaRoomException Si el campo no es valido
     */
    public static void validateId(CinemaRoomModel prmCinemaRoom) throws CinemaRoomException 
    {
        validateNull(prmCinemaRoom.getIdCinemaRoom(), CinemaRoomException.ID_CINEMA_ROOM);

        CinemaRoomException.throwException(
            (prmCinemaRoom.getIdCinemaRoom() <= 0), 
            CinemaRoomException.ID_CINEMA_ROOM,
            new Exception("Debe ser positivo")
        );
    }
}
