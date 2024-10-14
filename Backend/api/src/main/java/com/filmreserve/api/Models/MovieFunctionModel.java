package com.filmreserve.api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

/**
 * @author javiersolanop
 */
@Table
@Entity(name = "cin_presents_mov")
public class MovieFunctionModel implements Serializable, iJSON  {

    @EmbeddedId
    private MovieFunctionPK atrIdMovieFunction;

    @Column(name = "end_date")
    private LocalDate atrEndDate;

    @Column(name = "start_time")
    private LocalTime atrStartTime;

    @Column(name = "end_time")
    private LocalTime atrEndTime;

    @Column(name = "active")
    private Boolean atrActive;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    @MapsId("atrIdMovie")
    private MovieModel atrMovie;

    @ManyToOne
    @JoinColumn(name = "id_cinema_room")
    @MapsId("atrIdCinemaRoom")
    private CinemaRoomModel atrCinemaRoom;

    public MovieFunctionModel(){}

    public MovieFunctionPK getIdMovieFunction()
    {
        return atrIdMovieFunction;
    }

    public void setIdMovieFunction(MovieFunctionPK prmMovieFunctionPK)
    {
        atrIdMovieFunction = prmMovieFunctionPK;
    }

    public LocalDate getEndDate()
    {
        return atrEndDate;
    }

    public void setEndDate(LocalDate prmEndDate)
    {
        atrEndDate = prmEndDate;
    }

    public LocalTime getStartTime()
    {
        return atrStartTime;
    }

    public void setStartTime(LocalTime prmStartTime)
    {
        atrStartTime = prmStartTime;
    }

    public LocalTime getEndTime()
    {
        return atrEndTime;
    }

    public void setEndTime(LocalTime prmEndTime)
    {
        atrEndTime = prmEndTime;
    }

    public Boolean getActive()
    {
        return atrActive;
    }

    public void setActive(Boolean prmActive)
    {
        atrActive = prmActive;
    }

    public MovieModel getMovie()
    {
        return atrMovie;
    }

    public void setMovie(MovieModel prmMovie)
    {
        atrMovie = prmMovie;
    }

    public void setMovie(Long prmIdMovie)
    {
        atrMovie = new MovieModel();
        atrMovie.setIdMovie(prmIdMovie);
    }

    public CinemaRoomModel getCinemaRoom()
    {
        return atrCinemaRoom;
    }

    public void setCinemaRoom(CinemaRoomModel prmCinemaRoom)
    {
        atrCinemaRoom = prmCinemaRoom;
    }

    public void setCinemaRoom(Long prmIdCinemaRoom)
    {
        atrCinemaRoom = new CinemaRoomModel();
        atrCinemaRoom.setIdCinemaRoom(prmIdCinemaRoom);;
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
        objJson.add("idMovieFunction", atrIdMovieFunction.toJSON());
        objJson.add("endTime", atrEndTime.toString());
        objJson.add("active", atrActive);
        objJson.add("movie", atrMovie.toJSON());
        objJson.add("cinemaRoom", atrCinemaRoom.toJSON());
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
