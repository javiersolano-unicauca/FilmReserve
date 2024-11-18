package com.filmreserve.api.Dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.ReserveModel;
import com.filmreserve.api.Models.ReservePK;

/**
 * @author javiersolanop
 */
@Repository
public interface iReserveDao extends CrudRepository<ReserveModel, ReservePK> {

    /**
     *  Metodo para validar si existe la reserva de una funcion por la sala
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return La reserva si existe. De lo contrario null
     */
    @Query(
        value = "SELECT COUNT(cinema_room) > 0 FROM reserve WHERE id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom LIMIT 1",
        nativeQuery = true
    )
    Boolean existsByFunctionAndCinemaRoom(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    );

    /**
     *  Metodo para encontrar el primer registo de la reserva de una funcion por la sala
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return La reserva si existe. De lo contrario null
     */
    @Query(
        value = "SELECT * FROM reserve WHERE id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom LIMIT 1",
        nativeQuery = true
    )
    ReserveModel findFirstByFunctionAndCinemaRoom(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    );

    /**
     *  Metodo para encontrar los asientos de la reserva de una funcion por la sala
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return La reserva si existe. De lo contrario null
     */
    @Query(
        value = "SELECT * FROM reserve WHERE id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom",
        nativeQuery = true
    )
    List<ReserveModel> findAllByFunctionAndCinemaRoom(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    );

    /**
     *  Metodo para encontrar los asientos de todas las reservas de una funcion 
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     * 
     *  @return La reservas si existen. De lo contrario null
     */
    @Query(
        value = "SELECT * FROM reserve WHERE id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime",
        nativeQuery = true
    )
    List<ReserveModel> findAllByFunction(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    );

    /**
     *  Metodo para encontrar los asientos de la reserva de una funcion por la sala
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return Los asientos de la reserva si existe. De lo contrario null
     */
    @Query(
        value = "SELECT * FROM reserve WHERE id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom",
        nativeQuery = true
    )
    List<ReserveModel> findSeatsByFunction(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    );

    /**
     *  Metodo para encontrar  la reserva de una funcion por el campo 'active', 
     *  'start_date' y que se encuentre entre el rango de hora de inicio y fin. 
     *  De las horas a validar recibidas
     *  
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmStartDate  Recibe la fecha a validar
     *  @param prmStartTime  Recibe la hora de inicio a validar
     *  @param prmEndTime    Recibe la hora fin a validar
     * 
     *  @return La funcion si existe
     */
    @Query(
        value = "SELECT * FROM reserve WHERE cinema_room = :prmCinemaRoom" +
                " AND start_date = :prmStartDate  AND ((start_time <= :prmStartTime AND end_time >= :prmStartTime)" +
                " OR (start_time <= :prmEndTime AND end_time >= :prmEndTime))",
        nativeQuery = true
    )
    ReserveModel findByCinemaRoom(Integer prmCinemaRoom, LocalDate prmStartDate, LocalTime prmStartTime, LocalTime prmEndTime);

    /**
     *  Metodo para eliminar todas las reservas de una funcion por sala
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio de la funcion
     *  @param prmStartTime Recibe la hora de inicio de la funcion
     *  @param prmCinemaRoom Recibe la sala
     */
    @Query(
        value = "DELETE FROM reserve WHERE id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom",
        nativeQuery = true
    )
    void deleteByFunctionAndCinemaRoom(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    );
}
