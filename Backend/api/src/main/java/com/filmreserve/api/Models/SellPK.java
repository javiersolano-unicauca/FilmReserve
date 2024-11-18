package com.filmreserve.api.Models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

/**
 * @author javiersolanop
 */
@Embeddable
public class SellPK implements iJSON {

    @Column(name = "identification")
    private Long atrIdentification;

    @Embedded
    ReservePK atrIdReserve;

    public SellPK(){ atrIdReserve = new ReservePK(); }

    public SellPK(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        Character prmRow,
        Integer prmNumColumn
    ){
        atrIdentification = prmIdentification;
        atrIdReserve = new ReservePK(
            prmIdMovie, 
            prmStartDate,
            prmStartTime, 
            prmEndTime, 
            prmCinemaRoom, 
            prmRow, 
            prmNumColumn
        );
    }

    public void setReservePK(ReservePK prmReservePK)
    {
        atrIdReserve = prmReservePK;
    }

    public Long getIdentification()
    {
        return atrIdentification;
    }

    public void setIdentification(Long prmIdentification)
    {
        atrIdentification = prmIdentification;
    }

    public Long getIdMovie()
    {
        return atrIdReserve.getIdMovie();
    }

    public void setIdMovie(Long prmIdMovie)
    {
        atrIdReserve.setIdMovie(prmIdMovie);
    }

    public LocalDate getStartDate()
    {
        return atrIdReserve.getStartDate();
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrIdReserve.setStartDate(prmStartDate);
    }

    public LocalTime getStartTime()
    {
        return atrIdReserve.getStartTime();
    }

    public void setStartTime(LocalTime prmStartTime)
    {
        atrIdReserve.setStartTime(prmStartTime);
    }

    public LocalTime getEndTime()
    {
        return atrIdReserve.getEndTime();
    }

    public void setEndTime(LocalTime prmEndTime)
    {
        atrIdReserve.setEndTime(prmEndTime);
    }

    public Integer getCinemaRoom()
    {
        return atrIdReserve.getCinemaRoom();
    }

    public void setCinemaRoom(Integer prmCinemaRoom)
    {
        atrIdReserve.setCinemaRoom(prmCinemaRoom);
    }

    public Character getRow()
    {
        return atrIdReserve.getRow();
    }

    public void setRow(Character prmRow)
    {
        atrIdReserve.setRow(prmRow);
    }

    public Integer getNumColumn()
    {
        return atrIdReserve.getNumColumn();
    }

    public void setNumColumn(Integer prmNumColumn)
    {
        atrIdReserve.setNumColumn(prmNumColumn);
    }

    public JSON toJSON() throws Exception 
    {
        JSON objJson = atrIdReserve.toJSON();
        objJson.add("identification", atrIdentification);
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception 
    {
        return this.toJSON().equals(prmJson);
    }
}
