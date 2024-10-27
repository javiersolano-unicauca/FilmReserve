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

    @Column(name = "cinema_room")
    private Integer atrCinemaRoom;

    @Column(name = "start_date")
    private LocalDate atrStartDate;

    public MovieFunctionPK(){}

    public MovieFunctionPK(Long prmIdMovie, Integer prmCinemaRoom, LocalDate prmStartDate)
    {
        atrIdMovie = prmIdMovie;
        atrCinemaRoom = prmCinemaRoom;
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

    public Integer getCinemaRoom()
    {
        return atrCinemaRoom;
    }

    public void setCinemaRoom(Integer prmCinemaRoom)
    {
        atrCinemaRoom = prmCinemaRoom;
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
        objJson.add("cinemaRoom", atrCinemaRoom);
        objJson.add("startDate", atrStartDate);
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
