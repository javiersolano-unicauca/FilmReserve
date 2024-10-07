package com.filmreserve.api.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.TicketSellerModel;

/**
 *  @author javiersolanop
 */
@Repository
public interface iTicketSellerDao extends CrudRepository<TicketSellerModel, Long> {

}
