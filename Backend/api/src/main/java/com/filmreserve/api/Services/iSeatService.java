package com.filmreserve.api.Services;

import java.util.List;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.api.Models.SeatModel;
import com.filmreserve.api.Models.SeatPK;

/**
 * @author javiersolanop
 */
public interface iSeatService {

    /**
     *  Metodo para obtener un asiento por 'idSeat'
     *  
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmRow Recibe la fila
     *  @param prmNumColumn Recibe la columna
     *  
     *  @return Una instancia del modelo con la informacion del asiento si existe.
     *          De lo contrario null
     */
    public SeatModel getSeatModel(Integer prmCinemaRoom, Character prmRow, Integer prmNumColumn) throws Exception;

    /**
     *  Metodo para obtener un asiento por 'idSeat'
     *  
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmRow Recibe la fila
     *  @param prmNumColumn Recibe la columna
     *  
     *  @return Una instancia JSON con la clave 'getSeat' en 'true' y con la
     *          informacion del asiento si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getSeat(Integer prmCinemaRoom, Character prmRow, Integer prmNumColumn) throws Exception;

    /**
     *  Metodo para obtener los asientos de una sala
     * 
     *  @param prmCinemaRoom Recibe la sala
     * 
     *  @return una instancia JSON con los asientos de la sala si existen. 
     *          De lo contrario con una clave 'getCinema' en 'false'
     *          y su respectiva causa
     *  @see JSON
     */
    public LinkedListJSON getSeatsOfCinemaRoom(Integer prmCinemaRoom) throws Exception;

    /**
     *  Metodo para guardar un asiento
     * 
     *  @param prmSeatPK Recibe la referencia al id
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede guardar el asiento
     */
    public JSON save(SeatPK prmSeatPK) throws Exception;

    /**
     *  Metodo para eliminar un asiento
     * 
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmRow Recibe la fila
     *  @param prmNumColumn Recibe la columna
     * 
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON delete(Integer prmCinemaRoom, Character prmRow, Integer prmNumColumn) throws Exception;
}
