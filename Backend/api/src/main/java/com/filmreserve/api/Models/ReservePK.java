package com.filmreserve.api.Models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

/**
 *  @author javiersolanop
 */
@Embeddable
public class ReservePK implements iJSON {

    @Embedded
    private FunctionPK atrIdFunctionPK;

    @Embedded
    private SeatPK atrIdSeatPK;

    public ReservePK(){ 
        atrIdFunctionPK = new FunctionPK(); 
        atrIdSeatPK = new SeatPK();
    }

    public ReservePK(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        Character prmRow,
        Integer prmNumColumn
    ){
        atrIdFunctionPK = new FunctionPK(prmIdMovie, prmStartDate, prmStartTime, prmEndTime);
        atrIdSeatPK = new SeatPK(prmCinemaRoom, prmRow, prmNumColumn);
    }

    public FunctionPK getIdFunction()
    {
        return atrIdFunctionPK;
    }

    public void setIdFunction(FunctionPK prmFunctionPK)
    {
        atrIdFunctionPK = prmFunctionPK;
    }

    public SeatPK getIdSeat()
    {
        return atrIdSeatPK;
    }

    public void setIdSeat(SeatPK prmSeatPK)
    {
        atrIdSeatPK = prmSeatPK;
    }

    public Long getIdMovie()
    {
        return atrIdFunctionPK.getIdMovie();
    }

    public void setIdMovie(Long prmIdMovie)
    {
        atrIdFunctionPK.setIdMovie(prmIdMovie);
    }

    public LocalDate getStartDate()
    {
        return atrIdFunctionPK.getStartDate();
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrIdFunctionPK.setStartDate(prmStartDate);
    }

    public LocalTime getStartTime()
    {
        return atrIdFunctionPK.getStartTime();
    }

    public void setStartTime(LocalTime prmStartTime)
    {
        atrIdFunctionPK.setStartTime(prmStartTime);
    }

    public LocalTime getEndTime()
    {
        return atrIdFunctionPK.getEndTime();
    }

    public void setEndTime(LocalTime prmEndTime)
    {
        atrIdFunctionPK.setEndTime(prmEndTime);
    }

    public Integer getCinemaRoom()
    {
        return atrIdSeatPK.getCinemaRoom();
    }

    public void setCinemaRoom(Integer prmCinemaRoom)
    {
        atrIdSeatPK.setCinemaRoom(prmCinemaRoom);
    }

    public Character getRow()
    {
        return atrIdSeatPK.getRow();
    }

    public void setRow(Character prmRow)
    {
        atrIdSeatPK.setRow(prmRow);
    }

    public Integer getNumColumn()
    {
        return atrIdSeatPK.getNumColumn();
    }

    public void setNumColumn(Integer prmNumColumn)
    {
        atrIdSeatPK.setNumColumn(prmNumColumn);
    }

    @Override
    public JSON toJSON() throws Exception 
    {
        JSON objJson = atrIdFunctionPK.toJSON();
        objJson.add("cinemaRoom", getCinemaRoom());
        objJson.add("row", getRow());
        objJson.add("numColumn", getNumColumn());
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception 
    {
        return this.toJSON().equals(prmJson);
    }
}
