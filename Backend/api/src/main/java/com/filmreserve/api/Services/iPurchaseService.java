package com.filmreserve.api.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.PurchaseModel;

/**
 *  @author javiersolanop
 */
public interface iPurchaseService {

    /**
     *  Metodo para obtener los asientos reservados en la compra
     * 
     *  @param prmPurchasePK Recibe el id de la compra
     * 
     *  @return La lista con los asientos o vacia si no existen
     */
    LinkedListJSON getPurchasedSeats(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmReferenceId
    ) throws Exception;

    /**
     *  Metodo para obtener las compras de asientos
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmReferenceId Recibe la referencia del pago
     * 
     *  @return La compra o null si no existe
     */
    List<PurchaseModel> getPurchases(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmReferenceId
    ) throws Exception;

    /**
     *  Metodo para obtener los asientos reservados y la informacion de la compra
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmReferenceId Recibe la referencia del pago
     * 
     *  @return Una instancia JSON con la informacion de lac compra y sus asientos 
     *          si existe. De lo contrario una clave 'getPurchase' en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    JSON getPurchasedSeatsAndPurchase(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmReferenceId
    ) throws Exception;

    /**
     *  Metodo para validar si existe un cliente
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     * 
     *  @return 'true' si existen
     * 
     *  @throws Exception Con una instancia JSON de clave 'customer' en 'false'
     *          con su respectiva causa
     */
    public void validateCustomer(Long prmIdentification) throws Exception;

    /**
     *  Metodo para obtener una funcion de las reservas por sala
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     * 
     *  @return La funcion
     * 
     *  @throws Exception Con una instancia JSON de clave 'function' en 'false'
     *                    con su respectiva causa
     */
    public FunctionModel getFunctionModel(
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para validar la lista de asientos a comprar
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmEndTime Recibe la hora fin
     *  @param prmSeats Recibe los asientos a validar
     * 
     *  <p>
     *  Ejemplo del patron permitido para los asientos reservados:
     *  <blockquote>
     *      <pre> [A01, B23, C03] </pre>
     *  </blockquote>
     * 
     *  @return 'true'
     * 
     *  @throws Exception Con una instancia JSON de clave 'seats' en 'false'
     *          con su respectiva causa
     */
    public boolean validateSeatAvailability(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        String prmSeats
    ) throws Exception;

    /**
     *  Metodo para guardar una compra
     * 
     *  @param prmPurchaseModel Recibe la referencia a la compra
     *  @param prmPurchasedSeats Recibe los asientos reservados
     * 
     *  <p>
     *  Ejemplo del patron permitido para los asientos reservados:
     *  <blockquote>
     *      <pre> [A01, B23, C03] </pre>
     *  </blockquote>
     * 
     *  @return 'true'
     */
    boolean save(PurchaseModel prmPurchase,  String prmPurchasedSeats) throws Exception;

    /**
     *  Metodo para eliminar una compra
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmReferenceId Recibe la referencia del pago
     *  
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    JSON delete(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmReferenceId
    ) throws Exception;
}
