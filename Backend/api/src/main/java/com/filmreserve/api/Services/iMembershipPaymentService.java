package com.filmreserve.api.Services;

import java.time.LocalDate;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.MembershipPK;

/**
 *  @author javiersolanop
 */
public interface iMembershipPaymentService extends iPaymentService {

    /**
     *  Metodo para crear una referencia de compra
     * 
     *  @param prmMembershipPK Recibe el id de la membresia
     *  @param prmEndDate      Recibe la fecha de finalizacion de la membresia
     *  @param prmURLback      Recibe la URL para retorno
     * 
     *  @return Una instancia JSON con la clave 'pay' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     */
    JSON generatePayment(
        MembershipPK prmMembershipPK, 
        LocalDate prmEndDate, 
        String prmURLback
    ) throws Exception;

    /**
     *  Metodo para guardar la compra en el sistema
     * 
     *  @param prmReferenceId Recibe la referencia de compra
     *  @param prmPaymentId Recibe la referencia de pago
     */
    boolean saveMembership(String prmReferenceId, Long paymentId) throws Exception;
}
