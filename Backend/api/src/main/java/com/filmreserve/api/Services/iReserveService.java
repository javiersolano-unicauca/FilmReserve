package com.filmreserve.api.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.ReserveModel;

/**
 *  @author javiersolanop
 */
public interface iReserveService {

    /**
     *  Metodo para obtener la funcion de una reservada por la sala
     *  
     *  @param prmIdMovie Recibe la identificacion de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     *  
     *  @return Una instancia del modelo con la informacion de la reserva si existe.
     *          De lo contrario null
     */
    public FunctionModel getFunctionModel(
        Long prmIdMovie, 
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    ) throws Exception;

    /**
     *  Metodo para obtener la funcion por reserva y sala
     *  
     *  @param prmIdMovie Recibe la identificacion de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     *  
     *  @return Una instancia JSON con la clave 'getReserve' en 'true' y con la
     *          informacion de la funcion si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getFunction(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    ) throws Exception;

    /**
     *  Metodo para obtener los asientos de la sala de una reserva
     * 
     *  @param prmIdMovie    Recibe el id de la pelicula
     *  @param prmStartDate  Recibe la fecha de inicio
     *  @param prmStartTime  Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return La lista con los asientos o vacia si no existe la reserva
     */
    public LinkedListJSON getSeatsOfCinemaRoom(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        Integer prmCinemaRoom
    ) throws Exception;

    /**
     *  Metodo para obtener los asientos de la salas de una reserva
     * 
     *  @param prmIdMovie    Recibe el id de la pelicula
     *  @param prmStartDate  Recibe la fecha de inicio
     *  @param prmStartTime  Recibe la hora de inicio
     * 
     *  @return La lista con los asientos o vacia si no existe la reserva
     */
    public LinkedListJSON getSeatsOfFunction(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para guardar una reserva
     * 
     *  @param prmIdMovie   Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmEndTime Recibe la hora fin
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     *  @throws Exception Si no se puede guardar la funcion
     */
    public JSON save(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        LocalTime prmEndTime,
        Integer prmCinemaRoom
    ) throws Exception;

    /**
     *  Metodo para encontrar asientos sin reservar en las reservas de 
     *  una funcion por sala
     * 
     *  @param prmIdMovie    Recibe el id de la pelicula
     *  @param prmStartDate  Recibe la fecha de inicio
     *  @param prmStartTime  Recibe la hora de inicio
     *  @param prmEndTime    Recibe la hora de fin
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmListSeats  Recibe los asientos a encontrar
     * 
     *  <p>
     *  Ejemplo del patron permitido para los asientos:
     *  <blockquote>
     *      <pre> [A01, B23, C03] </pre>
     *  </blockquote>
     * 
     *  @return La lista con las reservas de los asientos encontrados.
     *          De lo contrario null si la cantidad de 
     *          asientos recibidos es superior a los de la sala o
     *          si alguno o todos los asientos estan reservados
     */
    public List<ReserveModel> findSeatsWithoutReserve(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        String prmListSeats
    ) throws Exception;

    /**
     *  Metodo para reservar asientos de una funcion
     * 
     *  @param prmIdMovie    Recibe el id de la pelicula
     *  @param prmStartDate  Recibe la fecha de inicio
     *  @param prmStartTime  Recibe la hora de inicio
     *  @param prmEndTime    Recibe la hora de fin
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmListSeats  Recibe los asientos a encontrar
     * 
     *  <p>
     *  Ejemplo del patron permitido para los asientos:
     *  <blockquote>
     *      <pre> [A01, B23, C03] </pre>
     *  </blockquote>
     * 
     *  @return 'true' 
     * 
     *  @throws Exception Si no existen reservas para la funcion
     */
    public boolean reserveSeats(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        Integer prmCinemaRoom, 
        String prmListSeats
    ) throws Exception;

    /**
     *  Metodo para eliminar una reserva
     * 
     *  @param prmIdMovie Recibe la identificacion de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON delete(
        Long prmIdMovie, 
        LocalDate prmStartDate,
        LocalTime prmStartTime, 
        Integer prmCinemaRoom
    ) throws Exception;
}
