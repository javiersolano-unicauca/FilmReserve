package com.filmreserve.api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

/**
 *  @author javiersolanop
 */
@Embeddable
public class FunctionPK implements iJSON {

    @Column(name = "id_movie")
    private Long atrIdMovie;

    @Column(name = "start_date")
    private LocalDate atrStartDate;

    @Column(name = "start_time")
    private LocalTime atrStartTime;

    @Column(name = "end_time")
    private LocalTime atrEndTime;

    public FunctionPK(){}

    public FunctionPK(
        Long prmIdMovie, 
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime
    ){
        atrIdMovie = prmIdMovie;
        atrStartDate = prmStartDate;
        atrStartTime = prmStartTime;
        atrEndTime = prmEndTime;
    }

    public Long getIdMovie()
    {
        return atrIdMovie;
    }

    public void setIdMovie(Long prmIdMovie)
    {
        atrIdMovie = prmIdMovie;
    }

    public LocalDate getStartDate()
    {
        return atrStartDate;
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrStartDate = prmStartDate;
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
        objJson.add("startDate", atrStartDate.toString());
        objJson.add("startTime", atrStartTime.toString());
        objJson.add("endTime", atrEndTime.toString());
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception {
        return this.toJSON().equalsJSON(prmJson);
    }
}
