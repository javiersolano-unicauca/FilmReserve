package com.filmreserve.api.Models;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *  @author javiersolanop
 */
@Table
@Entity(name = "seat")
public class SeatModel implements Serializable, iJSON {

    @EmbeddedId
    private SeatPK atrIdSeat;

    public SeatModel(){}

    public SeatModel(Integer prmCinemaRoom, Character prmRow, Integer prmNumColumn)
    {
        atrIdSeat.setCinemaRoom(prmCinemaRoom);
        atrIdSeat.setRow(prmRow);
        atrIdSeat.setNumColumn(prmNumColumn);
    }

    public void setIdSeat(SeatPK prmIdSeat)
    {
        atrIdSeat = prmIdSeat;
    }

    public Integer getCinemaRoom()
    {
        return atrIdSeat.getCinemaRoom();
    }

    public void setCinemaRoom(Integer prmCinemaRoom)
    {
        atrIdSeat.setCinemaRoom(prmCinemaRoom);
    }

    public Character getRow()
    {
        return atrIdSeat.getRow();
    }

    public void setRow(Character prmRow)
    {
        atrIdSeat.setRow(prmRow);
    }

    public Integer getNumColumn()
    {
        return atrIdSeat.getNumColumn();
    }

    public void setNumColumn(Integer prmNumColumn)
    {
        atrIdSeat.setNumColumn(prmNumColumn);
    }

    /**
     *  Metodo para obtener una instancia de 'JSON' con la 
     *  informacion de la sala
     *  @see JSON
     */
    @Override
    public JSON toJSON() throws Exception
    {
        JSON objJson = new JSON();
        objJson.add("numColumn", atrIdSeat.getNumColumn());
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
