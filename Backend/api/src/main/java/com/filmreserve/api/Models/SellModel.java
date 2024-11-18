package com.filmreserve.api.Models;

import java.io.Serializable;
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
@Entity(name = "sell")
public class SellModel implements Serializable, iJSON {

    @EmbeddedId
    private SellPK atrIdSell;
    
    @Column(name = "value")
    private Long atrValue;

    public SellModel(){}

    public SellModel(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        Character prmRow,
        Integer prmNumColumn,
        Long prmValue
    )
    {
        atrIdSell = new SellPK(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmEndTime, 
            prmCinemaRoom, 
            prmRow, 
            prmNumColumn
        );
        atrValue = prmValue;
    }

    public SellModel(SellPK prmIdSellPK, Long prmValue)
    {
        atrIdSell = prmIdSellPK;
        atrValue = prmValue;
    }

    public void setIdSell(SellPK prmSellPK)
    {
        atrIdSell = prmSellPK;
    }

    public Long getIdentification()
    {
        return atrIdSell.getIdentification();
    }

    public void setIdentification(Long prmIdentification)
    {
        atrIdSell.setIdentification(prmIdentification);
    }

    public Long getIdMovie()
    {
        return atrIdSell.getIdMovie();
    }

    public void setIdMovie(Long prmIdMovie)
    {
        atrIdSell.setIdMovie(prmIdMovie);
    }

    public LocalDate getStartDate()
    {
        return atrIdSell.getStartDate();
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrIdSell.setStartDate(prmStartDate);
    }

    public LocalTime getStartTime()
    {
        return atrIdSell.getStartTime();
    }

    public void setStartTime(LocalTime prmStartTime)
    {
        atrIdSell.setStartTime(prmStartTime);
    }

    public LocalTime getEndTime()
    {
        return atrIdSell.getEndTime();
    }

    public void setEndTime(LocalTime prmEndTime)
    {
        atrIdSell.setEndTime(prmEndTime);
    }

    public Integer getCinemaRoom()
    {
        return atrIdSell.getCinemaRoom();
    }

    public void setCinemaRoom(Integer prmCinemaRoom)
    {
        atrIdSell.setCinemaRoom(prmCinemaRoom);
    }

    public Character getRow()
    {
        return atrIdSell.getRow();
    }

    public void setRow(Character prmRow)
    {
        atrIdSell.setRow(prmRow);
    }

    public Integer getNumColumn()
    {
        return atrIdSell.getNumColumn();
    }

    public void setNumColumn(Integer prmNumColumn)
    {
        atrIdSell.setNumColumn(prmNumColumn);
    }

    public Long getValue()
    {
        return atrValue;
    }

    public void setValue(Long prmValue)
    {
        atrValue = prmValue;
    }

    @Override
    public JSON toJSON() throws Exception 
    {
        JSON objJson = atrIdSell.toJSON();
        objJson.add("value", atrValue);
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception 
    {
        return this.toJSON().equals(prmJson);
    }
}
