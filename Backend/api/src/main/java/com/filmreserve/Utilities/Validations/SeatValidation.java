package com.filmreserve.Utilities.Validations;

import com.filmreserve.Utilities.ModelsException.SeatException;
import com.filmreserve.api.Models.SeatModel;
import com.filmreserve.api.Models.SeatPK;

/**
 *  Clase para las validaciones de las salas
 *  @see SeatModel
 * 
 *  @author javiersolanop
 */
public class SeatValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmSeatPK Recibe la referencia del id
     * 
     *  @throws SeatException Cuando los campos no son validos en su totalidad
     */
    public static void validate(SeatPK prmSeatPK) throws SeatException
    {
        validateId(prmSeatPK);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws SeatException Si el campo esta vacio
     */
    private static void validateNull(Object prmField, int prmFieldType) throws SeatException
    {
        SeatException.throwException(
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
     *  @throws SeatException Si el campo esta vacio
     */
    private static void validateEmpty(Character prmField, int prmFieldType) throws SeatException
    {
        SeatException.throwException(
            prmField.toString().isEmpty(), 
            prmFieldType,
            new Exception("No debe estar vacio")
        );
    }

    /**
     *  @param prmSeatPK Recibe la referencia al id
     * 
     *  @throws SeatException Si el campo no es valido
     */
    public static void validateId(SeatPK prmSeatPK) throws SeatException 
    {
        validateNull(prmSeatPK.getCinemaRoom(), SeatException.CINEMA_ROOM);
        validateNull(prmSeatPK.getRow(), SeatException.ROW);
        validateNull(prmSeatPK.getNumColumn(), SeatException.NUM_COLUMN);
        validateEmpty(prmSeatPK.getRow(), SeatException.ROW);

        SeatException.throwException(
            ((prmSeatPK.getCinemaRoom() <= 0)
            && (prmSeatPK.getCinemaRoom() > 99)), 
            SeatException.CINEMA_ROOM,
            new Exception("Debe ser positivo y menor que 100")
        );

        SeatException.throwException(
            ((prmSeatPK.getRow() < 'A')
            && (prmSeatPK.getRow() > 'Z')), 
            SeatException.ROW,
            new Exception("Debe ser una letra mayuscula del abecedario [A - Z]")
        );

        SeatException.throwException(
            ((prmSeatPK.getNumColumn() <= 0)
            && (prmSeatPK.getNumColumn() > 99)), 
            SeatException.NUM_COLUMN,
            new Exception("Debe ser positivo y menor que 100")
        );
    }
}
