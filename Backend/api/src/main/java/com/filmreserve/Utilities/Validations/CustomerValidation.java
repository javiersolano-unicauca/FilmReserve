package com.filmreserve.Utilities.Validations;

import java.time.LocalDate;
import java.util.Date;

import com.filmreserve.Utilities.Exceptions.CustomerException;
import com.filmreserve.Utilities.Exceptions.TicketSellerException;
import com.filmreserve.Utilities.Exceptions.UserException;
import com.filmreserve.api.Models.CustomerModel;
import com.filmreserve.api.Models.TicketSellerModel;
import com.filmreserve.api.Models.UserModel;

/**  Clase para las validaciones de los clientes
 * 
 *  @see CustomerModel
 *  @see UserValidation
 * 
 *  @author javiersolanop
 */
public class CustomerValidation extends UserValidation {

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
        validateYear((CustomerModel) prmUser);
        validateMonth((CustomerModel) prmUser);
        validateDay((CustomerModel) prmUser);
        validatePhone((CustomerModel) prmUser);
    }

    protected static void validateEmpty(Object prmField, int prmFieldType) throws CustomerException
    {
        CustomerException.throwException(
            (prmField == null), 
            prmFieldType,
            new Exception("No debe estar vacio")
        );
    }

    private static MonthOfYearEnum[] getThirtyDays()
    {
        MonthOfYearEnum[] arrThirtyDays = {
            MonthOfYearEnum.APRIL,
            MonthOfYearEnum.JUNE,
            MonthOfYearEnum.SEPTEMBER,
            MonthOfYearEnum.NOVEMBER,
        };
        return arrThirtyDays;
    }

    public static void validateDay(CustomerModel prmUser) throws CustomerException
    {
        validateEmpty(prmUser.getDay(), CustomerException.DAY);

        int varDay = prmUser.getDay(),
            varMonth = prmUser.getMonth();
        MonthOfYearEnum[] arrThirtyDays = getThirtyDays();
        boolean varThrow = false;

        if((varDay < 1)) varThrow = true;
        else
        {
            if(varMonth == MonthOfYearEnum.FEBRUARY.getTypeData())
            {
                if(((prmUser.getYear() % 4) != 0) && (varDay > 28)) varThrow = true;
    
                else if(varDay > 29) varThrow = true;
            }
            else
            {
                for(MonthOfYearEnum varMonthOfYear: arrThirtyDays)
                    if((varMonthOfYear.getTypeData() == varMonth) && (varDay > 30)) 
                        varThrow = true;
                if(!varThrow && (varDay > 31)) varThrow = true;
            }           
        }

        CustomerException.throwException(
            varThrow,
            CustomerException.DAY,
            new Exception("Debe ser un dia valido")
        );
    }

    public static void validateMonth(CustomerModel prmUser) throws CustomerException
    {
        validateEmpty(prmUser.getMonth(), CustomerException.MONTH);
        int varMonth = prmUser.getMonth();

        CustomerException.throwException(
            ((varMonth < 1)
            || (varMonth > 12)),
            CustomerException.MONTH,
            new Exception("Debe ser un mes valido")
        );
    }

    public static void validateYear(CustomerModel prmUser) throws CustomerException
    {
        validateEmpty(prmUser.getYear(), CustomerException.YEAR);

        int varYear = prmUser.getYear(),
            varYearCurrent = LocalDate.now().getYear();

        CustomerException.throwException(
            ((varYear < (varYearCurrent - 100)) 
            || ((prmUser.getAge()) < 18)),
            CustomerException.YEAR,
            new Exception("Debe ser menor al año en curso. Tener al menos edad de 18 y no pasar los 100")
        );
    }

    public static void validatePhone(CustomerModel prmUser) throws CustomerException
    {
        validateEmpty(prmUser.getPhone(), CustomerException.PHONE);
        Long varPhone = prmUser.getPhone();

        CustomerException.throwException(
            ((varPhone < Long.valueOf("3000000000"))
            || (varPhone >= Long.valueOf("4000000000"))),
            CustomerException.PHONE,
            new Exception("Debe encontrarse entre 3000000000 y 4000000000")
        );
    }
}

