package com.filmreserve.api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

/**
 *  @author javiersolanop
 */
@Embeddable
public class MovieFunctionPK implements iJSON {

    @Column(name = "id_movie")
    private Long atrIdMovie;

    @Column(name = "id_cinema_room")
    private Long atrIdCinemaRoom;

    @Column(name = "start_date")
    private LocalDate atrStartDate;

    public MovieFunctionPK(){}

    public MovieFunctionPK(Long prmIdMovie, Long prmIdCinemaRoom, LocalDate prmStartDate)
    {
        atrIdMovie = prmIdMovie;
        atrIdCinemaRoom = prmIdCinemaRoom;
        atrStartDate = prmStartDate;
    }

    public Long getIdMovie()
    {
        return atrIdMovie;
    }

    public void setIdMovie(Long prmIdMovie)
    {
        atrIdMovie = prmIdMovie;
    }

    public Long getIdCinemaRoom()
    {
        return atrIdCinemaRoom;
    }

    public void setIdCinemaRoom(Long prmIdCinemaRoom)
    {
        atrIdCinemaRoom = prmIdCinemaRoom;
    }

    public LocalDate getStartDate()
    {
        return atrStartDate;
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrStartDate = prmStartDate;
    }

        /**
     *  Metodo para obtener una instancia de 'JSON' con la 
     *  informacion de la funcion
     *  @see JSON
     */
    @Override
    public JSON toJSON() throws Exception
    {
        JSON objJson = new JSON();
        objJson.add("idMovie", atrIdMovie);
        objJson.add("idCinemaRoom", atrIdCinemaRoom);
        objJson.add("startDate", atrStartDate);
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
