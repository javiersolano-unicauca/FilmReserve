package com.filmreserve.api.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.MovieModel;

/**
 * @author javiersolanop
 */
@Repository
public interface iMovieDao extends CrudRepository<MovieModel, Long>{

    /**
     *  Metodo para encontrar el nombre de una pelicula por el campo 'id_movie'
     * 
     *  @param prmIdMovie Recibe el id de la pelicula
     * 
     *  @return El nombre de la pelicula. De lo contrario null.
     */
    @Query(
        value = "SELECT name FROM movie WHERE id_movie = :prmIdMovie",
        nativeQuery = true
    )
    String findName(Long prmIdMovie);
}
