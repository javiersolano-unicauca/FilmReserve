package com.filmreserve.api.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.MovieModel;

/**
 * @author javiersolanop
 */
@Repository
public interface iMovieDao extends CrudRepository<MovieModel, Long>{

}
