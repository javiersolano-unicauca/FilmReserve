package com.filmreserve.api.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.UserModel;

/**
 *  @author javiersolanop
 */
@Repository
public interface iUserDao extends CrudRepository<UserModel, Long> {
}
