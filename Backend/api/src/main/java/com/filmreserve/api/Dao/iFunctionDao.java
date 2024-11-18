package com.filmreserve.api.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.FunctionPK;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author javiersolanop
 */
@Repository
public interface iFunctionDao extends CrudRepository<FunctionModel, FunctionPK> {

    /**
     *  Metodo para encontrar la funcion de una pelicula por la 
     *  fecha de inicio y hora de inicio
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     * 
     *  @return 'true' si existe. De lo contrario 'false'
     */
    @Query(
        value = "SELECT * FROM function WHERE "
        + " id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime",
        nativeQuery = true
    )
    FunctionModel find(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    );

    /**
     *  Metodo para encontrar la funcion de una pelicula por la 
     *  fecha de inicio, hora de inicio y el estado
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     * 
     *  @return 'true' si existe. De lo contrario 'false'
     */
    @Query(
        value = "SELECT * FROM function WHERE "
        + " id_movie = :prmIdMovie"
        + " AND start_date = :prmStartDate"
        + " AND start_time = :prmStartTime"
        + " AND active = :prmActive",
        nativeQuery = true
    )
    FunctionModel findByActive(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        Boolean prmActive
    );

    // /**
    //  *  Metodo para encontrar una funcion por el campo 'active', 'start_date' y que se encuentre
    //  *  entre el rango de hora de inicio y fin. De las horas a validar recibidas
    //  * 
    //  *  @param prmActive     Recibe el estado de la funcion
    //  *  @param prmStartDate  Recibe la fecha a validar
    //  *  @param prmStartTime  Recibe la hora de inicio a validar
    //  *  @param prmEndTime    Recibe la hora fin a validar
    //  * 
    //  *  @return La funcion si existe
    //  */
    // @Query(
    //     value = "SELECT * FROM function WHERE active = :prmActive" +
    //             " AND start_date = :prmStartDate AND ((start_time <= :prmStartTime AND end_time >= :prmStartTime)" +
    //             " OR (start_time <= :prmEndTime AND end_time >= :prmEndTime))",
    //     nativeQuery = true
    // )
    // FunctionModel find(Boolean prmActive, LocalDate prmStartDate, LocalTime prmStartTime, LocalTime prmEndTime);

    // /**
    //  * Metodo para encontrar una funcion por el id y el campo 'active'
    //  * 
    //  * @param prmIdMovie   Recibe el id de la pelicula
    //  * @param prmStartDate Recibe la fecha de inicio
    //  * @param prmStartTime Recibe la hora de inicio
    //  * @param prmActive    Recibe el estado de la funcion
    //  * 
    //  * @return La funcion si existe
    //  */
    // @Query(
    //     value = "SELECT * FROM function WHERE (id_movie = :prmIdMovie AND start_date = :prmStartDate"
    //     + " AND start_time = :prmStartTime AND active = :prmActive)", 
    //     nativeQuery = true
    // ) 
    // FunctionModel findByIdAndActive(Long prmIdMovie, LocalDate prmStartDate, LocalTime prmStartTime, Boolean prmActive);     
}

