package com.filmreserve.api.Dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.SellModel;
import com.filmreserve.api.Models.SellPK;

/**
 *  @author javiersolanop
 */
@Repository
public interface iSellDao extends CrudRepository<SellModel, SellPK> {

    /**
     *  Metodo para encontrar las ventas realizadas en una funcion por un cliente
     *   
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return Las lista con las ventas si existen. De lo contrario vacia
     */
    @Query(
        value = "SELECT * FROM sell WHERE identification = :prmIdentification"
        + " AND id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom",
        nativeQuery = true
    )
    List<SellModel> find(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    );

    /**
     *  Metodo para encontrar las ventas realizadas por un cliente
     *   
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return Las lista con las ventas si existen. De lo contrario vacia
     */
    @Query(
        value = "SELECT COUNT(cinema_room) > 0 FROM sell WHERE identification = :prmIdentification"
        + " AND id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom",
        nativeQuery = true
    )
    Boolean exists(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    );

    /**
     *  Metodo para eliminar las ventas realizadas por un cliente
     *   
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return Las lista con las ventas si existen. De lo contrario vacia
     */
    @Query(
        value = "DELETE FROM sell WHERE identification = :prmIdentification"
        + " AND id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom",
        nativeQuery = true
    )
    void deleteSell(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    );
}
