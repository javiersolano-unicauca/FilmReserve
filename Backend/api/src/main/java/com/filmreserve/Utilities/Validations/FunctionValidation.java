package com.filmreserve.Utilities.Validations;

import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.ModelsException.FunctionException;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.FunctionPK;

/**
 *  Clase para las validaciones de las funciones
 *  @see FunctionModel
 * 
 *  @author javiersolanop
 */
public class FunctionValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmFunctionPK Recibe la referencia del id
     * 
     *  @throws FunctionException Cuando los campos no son validos en su totalidad
     */
    public static void validate(FunctionPK prmFunctionPK) throws FunctionException
    {
        validateId(prmFunctionPK);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws FunctionException Si el campo esta vacio
     */
    private static void validateNull(Object prmField, int prmFieldType) throws FunctionException
    {
        FunctionException.throwException(
            prmField == null, 
            prmFieldType,
            new Exception("No debe estar nulo")
        );
    }

    /**
     *  @param prmFunctionPK Recibe la referencia del id
     * 
     *  @throws FunctionException Si el id no es valido
     */
    public static void validateId(FunctionPK prmFunctionPK) throws FunctionException 
    {   
        validateNull(prmFunctionPK.getIdMovie(), FunctionException.ID_MOVIE);
        validateNull(prmFunctionPK.getStartDate(), FunctionException.START_DATE);
        validateNull(prmFunctionPK.getStartTime(), FunctionException.START_TIME);
        validateNull(prmFunctionPK.getEndTime(), FunctionException.END_TIME);
        
        FunctionException.throwException(
            (prmFunctionPK.getIdMovie() <= 0), 
            FunctionException.ID_MOVIE,
            new Exception("Debe ser positivo")
        );

        LocalDate varNowDate = LocalDate.now();

        FunctionException.throwException(
            (varNowDate.isAfter(prmFunctionPK.getStartDate())),
            FunctionException.START_DATE,
            new Exception("Debe ser superior a la fecha actual")
        );

        String varMin = "14:00", varMax = "20:00";

        FunctionException.throwException(
            (prmFunctionPK.getStartTime().isBefore(LocalTime.parse(varMin))
            || prmFunctionPK.getStartTime().isAfter(LocalTime.parse(varMax))),
            FunctionException.START_TIME,
            new Exception("Debe ser estar entre las " + varMin + " y " + varMax + " horas")
        );

        varMin = "15:30"; varMax = "22:00";

        FunctionException.throwException(
            (prmFunctionPK.getEndTime().isBefore(LocalTime.parse(varMin))
            || prmFunctionPK.getEndTime().isAfter(LocalTime.parse(varMax))),
            FunctionException.END_TIME,
            new Exception("Debe ser estar entre las " + varMin + " y " + varMax + " horas")
        );

        FunctionException.throwException(
            (prmFunctionPK.getStartTime().isAfter(prmFunctionPK.getEndTime())),
            FunctionException.END_TIME,
            new Exception("Debe ser superior a la hora de inicio")
        );
    }
}
