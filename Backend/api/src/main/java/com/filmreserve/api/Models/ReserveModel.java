package com.filmreserve.api.Models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *  @author javiersolanop
 */
@Table
@Entity(name = "reserve")
public class ReserveModel implements iJSON {

    @EmbeddedId
    private ReservePK atrIdReservePK;

    @Column(name = "reserved")
    Boolean atrReserved;

    public ReserveModel(){ atrIdReservePK = new ReservePK(); }

    public ReserveModel(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        Character prmRow,
        Integer prmNumColumn,
        Boolean prmReserved
    ){
        atrIdReservePK = new ReservePK(prmIdMovie, prmStartDate, prmStartTime, prmEndTime, prmCinemaRoom, prmRow, prmNumColumn);
        atrReserved = prmReserved;
    }

    public void setIdReserve(ReservePK prmIdReservePK)
    {
        atrIdReservePK = prmIdReservePK;
    }

    public ReservePK findIdReserve()
    {
        return atrIdReservePK;
    }
    
    public Long getIdMovie()
    {
        return atrIdReservePK.getIdMovie();
    }

    public void setIdMovie(Long prmIdMovie)
    {
        atrIdReservePK.setIdMovie(prmIdMovie);
    }

    public LocalDate getStartDate()
    {
        return atrIdReservePK.getStartDate();
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrIdReservePK.setStartDate(prmStartDate);
    }

    public LocalTime getStartTime()
    {
        return atrIdReservePK.getStartTime();
    }

    public void setStartTime(LocalTime prmStartTime)
    {
        atrIdReservePK.setStartTime(prmStartTime);
    }

    public LocalTime getEndTime()
    {
        return atrIdReservePK.getEndTime();
    }

    public void setEndTime(LocalTime prmEndTime){
        atrIdReservePK.setEndTime(prmEndTime);
    }

    public Integer getCinemaRoom()
    {
        return atrIdReservePK.getCinemaRoom();
    }

    public void setCinemaRoom(Integer prmCinemaRoom)
    {
        atrIdReservePK.setCinemaRoom(prmCinemaRoom);
    }

    public Character getRow()
    {
        return atrIdReservePK.getRow();
    }

    public void setRow(Character prmRow)
    {
        atrIdReservePK.setRow(prmRow);
    }

    public Integer getNumColumn()
    {
        return atrIdReservePK.getNumColumn();
    }

    public void setNumColumn(Integer prmNumColumn)
    {
        atrIdReservePK.setNumColumn(prmNumColumn);
    }

    public Boolean getReserved()
    {
        return atrReserved;
    }

    public void setReserved(Boolean prmReserved)
    {
        atrReserved = prmReserved;
    }

    @Override
    public JSON toJSON() throws Exception 
    {
        JSON objJson = new JSON();
        objJson.add("numColumn", atrIdReservePK.getNumColumn());
        objJson.add("reserved", atrReserved);
        return objJson;
    }

    /**
     *  Metodo para obtener una instancia de 'JSON' con la informacion del numero de columna
     *  y el estado de la reserva
     */
    public JSON toJSONComplete() throws Exception
    {
        JSON objJson = atrIdReservePK.toJSON();
        objJson.add("reserved", atrReserved);
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception 
    {
        return toJSONComplete().equalsJSON(prmJson);
    }
}
