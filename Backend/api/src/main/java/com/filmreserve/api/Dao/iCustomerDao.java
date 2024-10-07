package com.filmreserve.api.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.CustomerModel;

/**
 * @author javiersolanop
 */
@Repository
public interface iCustomerDao extends CrudRepository<CustomerModel, Long> {
 
}
