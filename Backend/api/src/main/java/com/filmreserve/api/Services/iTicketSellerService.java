package com.filmreserve.api.Services;

import com.filmreserve.Utilities.Arrays.JSON.JSON;

/**
 * @author javiersolanop
 */
public interface iTicketSellerService extends iUserService {

    /**
     *  Metodo para actualizar el turno de un taquillero
     *  
     *  @param prmIdentification Recibe la identificacion
     *  @param prmTurn Recibe el nuevo turno
     * 
     *  @return Una instancia JSON con la clave 'update' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede actualizar el turno
     */
    public JSON updateTurn(Long prmIdentification, String prmTurn) throws Exception;
}
