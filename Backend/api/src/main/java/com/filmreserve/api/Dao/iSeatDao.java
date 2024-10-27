package com.filmreserve.api.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.SeatModel;
import com.filmreserve.api.Models.SeatPK;

import java.util.List;

/**
 * @author javiersolanop
 */
@Repository
public interface iSeatDao extends CrudRepository<SeatModel, SeatPK> {

    /**
     *  Metodo para buscar los asientos de una sala
     * 
     *  @param prmCinemaRoom Recibe la referencia a la sala
     * 
     *  @return La lista con los asientos o vacia si no tiene
     */
    @Query(
        value = "SELECT * FROM seat WHERE cinema_room = :prmCinemaRoom",
        nativeQuery = true
    )
    List<SeatModel> findByCinemaRoom(Integer prmCinemaRoom);
}
