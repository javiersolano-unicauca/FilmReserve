package com.filmreserve.api.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.MovieFunctionModel;
import com.filmreserve.api.Models.MovieFunctionPK;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author javiersolanop
 */
@Repository
public interface iMovieFunctionDao extends CrudRepository<MovieFunctionModel, MovieFunctionPK> {

        /**
         * Metodo para saber si existe una funcion por campo 'cinemaRoom', 'active', que
         * se
         * encuentre entre el rango de la fecha de inicio y fin, y hora de inicio y fin
         * 
         * @param prmCinemaRoom Recibe la sala
         * @param prmActive     Recibe el estado de la funcion
         * @param prmDate       Recibe la fecha a validar
         * @param prmTime       Recibe la hora a validar
         * 
         * @return La funcion si existe
         */
        @Query(
                value = "SELECT * FROM sea_presents_mov WHERE (cinema_room = :prmCinemaRoom AND active = :prmActive)" +
                        "AND (start_date <= :prmDate AND end_date >= :prmDate) AND (start_time <= :prmTime AND end_time >= :prmTime)",
                nativeQuery = true
        )
        MovieFunctionModel find(Integer prmCinemaRoom, Boolean prmActive, LocalDate prmDate, LocalTime prmTime);

        /**
         * Metodo para saber si existe una funcion por campo 'cinemaRoom', 'active', y
         * que se
         * encuentre entre el rango de la fecha de inicio y fin
         * 
         * @param prmIdMovie   Recibe el id de la pelicula
         * @param prmStartDate Recibe la fecha de inicio
         * @param prmStartTime Recibe la hora de inicio
         * @param prmActive    Recibe el estado de la funcion
         * 
         * @return La funcion si existe
         */
        @Query(
                value = "SELECT * FROM sea_presents_mov WHERE (id_movie = :prmIdMovie AND start_date = :prmStartDate"
                + " AND start_time = :prmStartTime AND active = :prmActive)", 
                nativeQuery = true
        ) 
        MovieFunctionModel findByIdMovieAndStartDateAndStartTimeAndActive(Long prmIdMovie, LocalDate prmStartDate, LocalTime prmStartTime, Boolean prmActive);
}

