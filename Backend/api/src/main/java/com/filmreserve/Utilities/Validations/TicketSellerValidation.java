package com.filmreserve.Utilities.Validations;

import com.filmreserve.Utilities.ModelsException.TicketSellerException;
import com.filmreserve.Utilities.ModelsException.UserException;
import com.filmreserve.api.Models.TicketSellerModel;
import com.filmreserve.api.Models.UserModel;

/**
 *  Clase para las validaciones de los taquilleros
 * 
 *  @see TicketSellerModel
 *  @see UserValidation
 * 
 *  @author javiersolanop
 */
public class TicketSellerValidation extends UserValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmTicketSeller Recibe la referencia del taquillero
     * 
     *  @throws TicketSellerException Cuando los campos no son validos en su totalidad
     */
    public static void validate(UserModel prmUser) throws UserException
    {
        validateId(prmUser);
        validateFirstName(prmUser);
        validateSecondName(prmUser);
        validateFirstSurname(prmUser);
        validateSecondSurname(prmUser);
        validatePassword(prmUser);
        validateTurn((TicketSellerModel) prmUser);
    }

    protected static void validateNull(Object prmField, int prmFieldType) throws TicketSellerException
    {
        TicketSellerException.throwException(
            (prmField == null), 
            prmFieldType,
            new Exception("No debe estar nulo")
        );
    }

    protected static void validateEmpty(String prmField, int prmFieldType) throws TicketSellerException
    {
        TicketSellerException.throwException(
            prmField.isEmpty(), 
            prmFieldType,
            new Exception("No debe estar nulo")
        );
    }

    /**
     *  @param prmTicketSeller Recibe la referencia del taquillero
     * 
     *  @throws TicketSellerException Si el campo no es valido
     */
    public static void validateTurn(TicketSellerModel prmTicketSeller) throws TicketSellerException
    {
        validateNull(prmTicketSeller.getTurn(), TicketSellerException.TURN);
        Boolean varThrow = true;

        for(TurnEnum varTurn: TurnEnum.values())
            if(varTurn.getTypeData().equals(prmTicketSeller.getTurn()))
                varThrow = false;

        TicketSellerException.throwException(
            varThrow,
            TicketSellerException.TURN,
            new Exception("Turno no valido")
        );
    }
}
