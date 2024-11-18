package com.filmreserve.api.Dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.PurchaseModel;
import com.filmreserve.api.Models.PurchasePK;

/**
 *  @author javiersolanop
 */
@Repository
public interface iPurchaseDao extends CrudRepository<PurchaseModel, PurchasePK> {

    /**
     *  Metodo para encontrar las compras realizadas en una funcion por un cliente
     *   
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmReferenceId Recibe la referencia de la compra
     * 
     *  @return Las lista con las compras si existen. De lo contrario vacia
     */
    @Query(
        value = "SELECT * FROM purchase WHERE identification = :prmIdentification"
        + " AND id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom"
        + " AND reference_id = :prmReferenceId",
        nativeQuery = true
    )
    List<PurchaseModel> find(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom,
        String prmReferenceId
    );

    /**
     *  Metodo para encontrar las compras realizadas por un cliente
     *   
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmReferenceId Recibe la referencia de la compra
     * 
     *  @return Las lista con las compras si existen. De lo contrario vacia
     */
    @Query(
        value = "SELECT COUNT(cinema_room) > 0 FROM purchase WHERE identification = :prmIdentification"
        + " AND id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom"
        + " AND reference_id = :prmReferenceId",
        nativeQuery = true
    )
    Boolean exists(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom,
        String prmReferenceId
    );

    /**
     *  Metodo para eliminar las compras realizadas por un cliente
     *   
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmReferenceId Recibe la referencia de la compra
     * 
     *  @return Las lista con las compras si existen. De lo contrario vacia
     */
    @Query(
        value = "DELETE FROM purchase WHERE identification = :prmIdentification"
        + " AND id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND cinema_room = :prmCinemaRoom"
        + " AND reference_id = :prmReferenceId",
        nativeQuery = true
    )
    void deletePurchase(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Integer prmCinemaRoom,
        String prmReferenceId
    );
}
