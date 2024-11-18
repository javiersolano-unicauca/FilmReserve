package com.filmreserve.api.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.SellModel;

/**
 *  @author javiersolanop
 */
public interface iSellService {

    /**
     *  Metodo para obtener los asientos reservados en la venta
     * 
     *  @param prmPurchasePK Recibe el id de la venta
     * 
     *  @return La lista con los asientos o vacia si no existen
     */
    LinkedListJSON getSelledSeats(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para obtener las ventas de asientos
     * 
     *  @param prmIdentification Recibe la identificacion del taquillero
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     * 
     *  @return La venta o null si no existe
     */
    List<SellModel> getSales(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para obtener los asientos reservados y la informacion de la venta
     * 
     *  @param prmIdentification Recibe la identificacion del taquillero
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     * 
     *  @return Una instancia JSON con la informacion de lac venta y sus asientos 
     *          si existe. De lo contrario una clave 'getPurchase' en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    JSON getSelledSeatsAndSell(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para validar si existe un taqillero
     * 
     *  @param prmIdentification Recibe la identificacion del taqillero
     * 
     *  @return 'true' si existen
     * 
     *  @throws Exception Con una instancia JSON de clave 'customer' en 'false'
     *          con su respectiva causa
     */
    public void validateTicketSeller(Long prmIdentification) throws Exception;

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
     *  Metodo para validar la lista de asientos a dender
     * 
     *  @param prmIdentification Recibe la identificacion del taquillero
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
     *  Metodo para guardar una venta
     * 
     *  @param prmIdentification Recibe la identificacion del taquillero
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmSelledSeats Recibe los asientos reservados
     * 
     *  <p>
     *  Ejemplo del patron permitido para los asientos reservados:
     *  <blockquote>
     *      <pre> [A01, B23, C03] </pre>
     *  </blockquote>
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    JSON save(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmSelledSeats
    ) throws Exception;

    /**
     *  Metodo para eliminar una venta
     * 
     *  @param prmIdentification Recibe la identificacion del taquillero
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
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
        LocalTime prmStartTime
    ) throws Exception;
}
