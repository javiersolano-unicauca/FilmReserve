package com.filmreserve.Utilities.Validations;

import java.time.LocalDate;

import com.filmreserve.Utilities.ModelsException.MembershipException;
import com.filmreserve.api.Models.MembershipPK;

/**
 *  @author javiersolanop
 */
public class MembershipValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmMembershipPK Recibe la referencia del id
     *  @param prmEndDate Recibe la fecha de finalizacion
     * 
     *  @throws MembershipException Cuando los campos no son validos en su totalidad
     */
    public static void validate(MembershipPK prmMembershipPK, LocalDate prmEndDate) throws MembershipException
    {
        validateId(prmMembershipPK);
        validateEndDate(prmMembershipPK, prmEndDate);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws MembershipException Si el campo esta vacio
     */
    private static void validateNull(Object prmField, int prmFieldType) throws MembershipException
    {
        MembershipException.throwException(
            prmField == null, 
            prmFieldType,
            new Exception("No debe estar nulo")
        );
    }

    /**
     *  @param prmMembership Recibe la referencia de la membresia
     * 
     *  @throws MembershipException Si el campo no es valido
     */
    public static void validateId(MembershipPK prmMembershipPK) throws MembershipException 
    {
        validateNull(prmMembershipPK.getIdentification(), MembershipException.IDENTIFICATION);
        validateNull(prmMembershipPK.getStartDate(), MembershipException.START_DATE);

        MembershipException.throwException(
            (prmMembershipPK.getIdentification() <= 0), 
            MembershipException.IDENTIFICATION,
            new Exception("Debe ser positivo")
        );

        LocalDate varNowDate = LocalDate.now();

        MembershipException.throwException(
            (varNowDate.isAfter(prmMembershipPK.getStartDate())),
            MembershipException.START_DATE,
            new Exception("Debe ser igual o superior a la fecha actual")
        );
    }

    /**
     *  @param prmMembershipPK Recibe la referencia del id
     *  @param prmEndDate Recibe la fecha de finalizacion
     * 
     *  @throws MembershipException Si el campo no es valido
     */
    public static void validateEndDate(MembershipPK prmMembershipPK, LocalDate prmEndDate) throws MembershipException
    {
        validateNull(prmEndDate, MembershipException.END_DATE);
        LocalDate varNowDate = LocalDate.now();

        MembershipException.throwException(
            (varNowDate.isAfter(prmEndDate)),
            MembershipException.END_DATE,
            new Exception("Debe ser superior a la fecha actual")
        );

        MembershipException.throwException(
            (prmMembershipPK.getStartDate().isAfter(prmEndDate)),
            MembershipException.END_DATE,
            new Exception("Debe ser superior a la fecha de inicio")
        );
    }
}
