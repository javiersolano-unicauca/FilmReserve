package com.filmreserve.api.Services;

import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.PurchasePK;

/**
 *  @author javiersolanop
 */
public interface iPurchasePaymentService extends iPaymentService {

    /**
     *  Metodo para crear una referencia de compra
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe la identificacion de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmPurchasedSeats Recibe los asientos reservados
     *  @param prmURLback Recibe la URL para retorno
     *  
     *  <p>
     *  Ejemplo del patron permitido para los asientos reservados:
     *  <blockquote>
     *      <pre> [A1, B2, C3] </pre>
     *  </blockquote>
     * 
     *  @return Una instancia JSON con la clave 'pay' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     */
    JSON generatePayment(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmPurchasedSeats,
        String prmURLback
    ) throws Exception;

    /**
     *  Metodo para guardar la compra en el sistema
     * 
     *  @param prmReferenceId Recibe la referencia de compra
     *  @param prmPaymentId Recibe la referencia de pago
     */
    boolean savePurchase(String prmReferenceId, Long paymentId) throws Exception;
}
