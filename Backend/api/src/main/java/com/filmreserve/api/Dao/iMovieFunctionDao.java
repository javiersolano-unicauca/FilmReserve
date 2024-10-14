package com.filmreserve.api.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.MovieFunctionModel;
import com.filmreserve.api.Models.MovieFunctionPK;

/**
 * @author javiersolanop
 */
@Repository
public interface iMovieFunctionDao extends CrudRepository<MovieFunctionModel, MovieFunctionPK> {

}
