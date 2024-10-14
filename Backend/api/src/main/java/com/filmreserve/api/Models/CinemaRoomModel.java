package com.filmreserve.api.Models;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *  @author javiersolanop
 */
@Table
@Entity(name = "cinema_room")
public class CinemaRoomModel  implements Serializable, iJSON {

    @Id
    @Column(name = "id_cinema_room")
    private Long atrIdCinemaRoom;

    public CinemaRoomModel(){}

    public Long getIdCinemaRoom()
    {
        return atrIdCinemaRoom;
    }

    public void setIdCinemaRoom(Long prmIdCinemaRoom)
    {
        atrIdCinemaRoom = prmIdCinemaRoom;
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
        objJson.add("idCinemaRoom", atrIdCinemaRoom);
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
