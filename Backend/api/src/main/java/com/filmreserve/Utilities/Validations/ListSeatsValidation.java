package com.filmreserve.Utilities.Validations;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Exceptions.ChainOfCharacterException;
import com.filmreserve.Utilities.ModelsException.ListSeatsException;
import com.filmreserve.api.Models.PurchaseModel;

/**
 *  Clase para las validaciones de la clase 'PurchaseModel'
 *  @see PurchaseModel
 * 
 *  @author javiersolanop
 */
public class ListSeatsValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmPurchasedSeats Recibe los asientos comprados
     * 
     *  @throws ListSeatsException Cuando los campos no son validos en su totalidad
     */
    public static void validate(String prmPurchasedSeats) throws ListSeatsException
    {
        validatePurchasedSeats(prmPurchasedSeats);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws ListSeatsException Si el campo esta vacio
     */
    private static void validateNull(Object prmField, int prmFieldType) throws ListSeatsException
    {
        ListSeatsException.throwException(
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
     *  @throws ListSeatsException Si el campo esta vacio
     */
    private static void validateEmpty(String prmField, int prmFieldType) throws ListSeatsException
    {
        ListSeatsException.throwException(
            prmField.isEmpty(), 
            prmFieldType,
            new Exception("No debe estar vacio")
        );
    }

    /**
     *  Metodo para validar el nombre de un asiento reservado
     * 
     *  @param prmSeatName Recibe el nombre del asiento a validar
     * 
     *  @throws ListSeatsException Si el nombre no es valido
     */
    private static void validateSeatName(String prmSeatName) throws ListSeatsException
    {
        ListSeatsException.throwException(
            (prmSeatName.length() != 3), 
            ListSeatsException.LIST_SEATS,
            new Exception("Cada asiento debe estar compuesto por 1 caracter para la fila y 2 para la columna")
        );

        ListSeatsException.throwException(
            ((prmSeatName.charAt(0) < 'A') || (prmSeatName.charAt(0) > 'Z')),
            ListSeatsException.LIST_SEATS,
            new Exception("La fila debe ser una letra de [A - Z]")
        );

        String varColumn = ChainOfCharacter.substring(prmSeatName, 1, 2);
 
        ListSeatsException.throwException(
            (varColumn.contains("-") 
            || (!ChainOfCharacter.isNumberInt(varColumn))),
            ListSeatsException.LIST_SEATS,
            new Exception("La columna debe ser un numero entero positivo y menor que 100")
        );
    }

    /**
     *  @param prmPurchasedSeats Recibe los asientos
     * 
     *  @throws ListSeatsException Si el campo no es valido
     */
    public static void validatePurchasedSeats(String prmPurchasedSeats) throws ListSeatsException
    {
        validateNull(prmPurchasedSeats, ListSeatsException.LIST_SEATS);
        validateEmpty(prmPurchasedSeats, ListSeatsException.LIST_SEATS);

        try{ 

            String[] arrPurchasedSeats = ChainOfCharacter.listToArray(prmPurchasedSeats);
            
            ListSeatsException.throwException(
                (arrPurchasedSeats == null),
                ListSeatsException.LIST_SEATS,
                new Exception("No debe estar vacio")
            );  

            for(String varPurchasedSeat: arrPurchasedSeats)
                validateSeatName(varPurchasedSeat);

        }catch(ChainOfCharacterException e)
        {
            ListSeatsException.throwException(ListSeatsException.LIST_SEATS, e);
        }
    }
}
