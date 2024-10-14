package com.filmreserve.api.Services;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.CinemaRoomModel;

/**
 * @author javiersolanop
 */
public interface iCinemaRoomService {

    /**
     *  Metodo para obtener una pelicula por 'idCinemaRoom'
     *  
     *  @param prmIdCinemaRoom Recibe la identificacion
     *  
     *  @return Una instancia del modelo con la informacion de la sala si existe.
     *          De lo contrario null
     */
    public CinemaRoomModel getCinemaRoomModel(Long prmIdCinemaRoom) throws Exception;

    /**
     *  Metodo para obtener una sala por 'id_cinema_room'
     *  
     *  @param prmIdCinemaRoom Recibe la identificacion
     *  
     *  @return Una instancia JSON con la clave 'getCinemaRoom' en 'true' y con la
     *          informacion de la sala si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getCinemaRoom(Long prmIdCinemaRoom) throws Exception;

    /**
     *  Metodo para guardar una sala
     * 
     *  @param prmCinemaRoom Recibe los datos de la sala
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede guardar la sala
     */
    public JSON save(CinemaRoomModel prmCinemaRoom) throws Exception;

    /**
     *  Metodo para eliminar una sala
     * 
     *  @param prmIdCinemaRoom Recibe la identificacion
     * 
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON delete(Long prmIdCinemaRoom) throws Exception;
}
