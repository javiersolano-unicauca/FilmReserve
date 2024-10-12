package com.filmreserve.Utilities.Validations;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Exceptions.ChainOfCharacterException;
import com.filmreserve.Utilities.ModelsException.UserException;
import com.filmreserve.api.Models.UserModel;

/**
 *  Clase para las validaciones de los usuarios
 *  @see UserModel
 * 
 *  @author javiersolanop
 */
public class UserValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Cuando los campos no son validos en su totalidad
     */
    public static void validate(UserModel prmUser) throws UserException
    {
        validateId(prmUser);
        validateFirstName(prmUser);
        validateSecondName(prmUser);
        validateFirstSurname(prmUser);
        validateSecondSurname(prmUser);
        validatePassword(prmUser);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws UserException Si el campo esta vacio
     */
    protected static void validateNull(Object prmField, int prmFieldType) throws UserException
    {
        UserException.throwException(
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
     *  @throws UserException Si el campo esta vacio
     */
    protected static void validateEmpty(String prmField, int prmFieldType) throws UserException
    {
        UserException.throwException(
            prmField.isEmpty(), 
            prmFieldType,
            new Exception("No debe estar vacio")
        );
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateId(UserModel prmUser) throws UserException 
    {
        validateNull(prmUser.getIdentification(), UserException.IDENTIFICATION);

        UserException.throwException(
            (prmUser.getIdentification() <= 0), 
            UserException.IDENTIFICATION,
            new Exception("Debe ser positivo")
        );
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateFirstName(UserModel prmUser) throws UserException
    {
        validateNull(prmUser.getFirstName(), UserException.FIRST_NAME);
        validateEmpty(prmUser.getFirstName(), UserException.FIRST_NAME);

        try{
            ChainOfCharacter.containsLettersWithException(prmUser.getFirstName());
        }
        catch(ChainOfCharacterException e){
            UserException.throwException(UserException.FIRST_NAME, e);
        } 
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateSecondName(UserModel prmUser) throws UserException
    {
        try{
            ChainOfCharacter.containsLettersWithException(prmUser.getSecondName());
        }
        catch(ChainOfCharacterException e){
            UserException.throwException(UserException.SECOND_NAME, e);
        } 
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateFirstSurname(UserModel prmUser) throws UserException
    {
        validateNull(prmUser.getFirstSurname(), UserException.FIRST_SURNAME);
        validateEmpty(prmUser.getFirstSurname(), UserException.FIRST_SURNAME);

        try{
            ChainOfCharacter.containsLettersWithException(prmUser.getFirstSurname());
        }
        catch(ChainOfCharacterException e){
            UserException.throwException(UserException.FIRST_SURNAME, e);
        } 
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateSecondSurname(UserModel prmUser) throws UserException
    {
        try{
            ChainOfCharacter.containsLettersWithException(prmUser.getSecondSurname());
        }
        catch(ChainOfCharacterException e){
            UserException.throwException(UserException.SECOND_SURNAME, e);
        } 
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validatePassword(UserModel prmUser) throws UserException
    {
        validateNull(prmUser.getPassword(), UserException.PASSWORD);

        UserException.throwException(
            (prmUser.getPassword().length() < 8),
            UserException.PASSWORD,
            new Exception("Debe contener por lo menos ocho caracteres")  
        ); 
    }
}
