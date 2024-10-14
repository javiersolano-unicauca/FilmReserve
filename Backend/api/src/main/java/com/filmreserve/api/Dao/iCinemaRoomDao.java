package com.filmreserve.api.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.CinemaRoomModel;

/**
 * @author javiersolanop
 */
@Repository
public interface iCinemaRoomDao extends CrudRepository<CinemaRoomModel, Long> {

}
