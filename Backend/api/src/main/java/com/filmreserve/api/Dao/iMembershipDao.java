package com.filmreserve.api.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.filmreserve.api.Models.MembershipModel;
import com.filmreserve.api.Models.MembershipPK;

/**
 * @author javiersolanop
 */
@Repository
public interface iMembershipDao extends CrudRepository<MembershipModel, MembershipPK> {

    /**
     *  Metodo para obtener el historial de membresias de un cliente
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     * 
     *  @return La lista con la memebresias o vacia si no tiene
     */
    @Query(
        value = "SELECT * FROM membership WHERE identification = :prmIdentification",
        nativeQuery = true
    )
    List<MembershipModel> findByIdentification(Long prmIdentification);

    /**
     *  Metodo para encontrar la membresia reciente por los campos 'identification' y 'active'
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmActive Recibe el estado de la membresia
     * 
     *  @return La membresia si existe
     */
    @Query(
        value = "SELECT identification, MAX(start_date) as start_date, end_date, active"
        + " FROM membership WHERE (identification = :prmIdentification AND active = :prmActive)",
        nativeQuery = true
    )
    MembershipModel findMembershipRecent(Long prmIdentification, Boolean prmActive);

    /**
     *  Metodo para encontrar una membresia por el campo 'identification' y 
     *  que se encuentre en el rango de fecha de inicio y fin
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmDate Recibe la fecha a validar
     * 
     *  @return La membresia si existe
     */
    @Query(
        value = "SELECT * FROM membership WHERE identification = :prmIdentification"
        + " AND (start_date <= :prmDate AND end_date >= :prmDate)",
        nativeQuery = true
    )
    MembershipModel find(Long prmIdentification, LocalDate prmDate);
}
