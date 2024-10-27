package com.filmreserve.api.Models;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *  @author javiersolanop
 */
@Table
@Entity(name = "seat")
public class SeatModel  implements Serializable, iJSON {

    @EmbeddedId
    private SeatPK atrIdSeat;

    @Column(name = "reserved")
    private Boolean atrReserved;

    public SeatModel(){}

    public void setIdSeat(SeatPK prmIdSeat)
    {
        atrIdSeat = prmIdSeat;
    }

    public Integer getCinemaRoom()
    {
        return atrIdSeat.getCinemaRoom();
    }

    public Character getRow()
    {
        return atrIdSeat.getRow();
    }

    public Integer getNumColumn()
    {
        return atrIdSeat.getNumColumn();
    }

    public Boolean getReserved()
    {
        return atrReserved;
    }

    public void setReserved(Boolean prmReserved)
    {
        atrReserved = prmReserved;
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
        objJson.add("reserved", atrReserved);
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
