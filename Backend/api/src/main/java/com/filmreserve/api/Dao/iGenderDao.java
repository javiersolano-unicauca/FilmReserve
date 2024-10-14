package com.filmreserve.api.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.GenderModel;
import com.filmreserve.api.Models.GenderModelPK;

/**
 * @author javiersolanop
 */
@Repository
public interface iGenderDao extends CrudRepository<GenderModel, GenderModelPK> {

}
