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
@Entity(name = "purchase")
public class PurchaseModel implements Serializable, iJSON {

    @EmbeddedId
    private PurchasePK atrIdPurchase;

    @Column(name = "payment_id")
    private Long atrPaymentId;
    
    @Column(name = "value")
    private Long atrValue;

    public PurchaseModel(){ atrIdPurchase = new PurchasePK(); }

    public PurchaseModel(
        Long prmIdentification,
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        Character prmRow,
        Integer prmNumColumn,
        String prmReferenceId,
        Long prmPaymentId,
        Long prmValue
    )
    {
        atrIdPurchase = new PurchasePK(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmEndTime, 
            prmCinemaRoom, 
            prmRow, 
            prmNumColumn, 
            prmReferenceId
        );
        atrPaymentId = prmPaymentId;
        atrValue = prmValue;
    }

    public void setIdPurchase(PurchasePK prmPurchasePK)
    {
        atrIdPurchase = prmPurchasePK;
    }

    public Long getIdentification()
    {
        return atrIdPurchase.getIdentification();
    }

    public void setIdentification(Long prmIdentification)
    {
        atrIdPurchase.setIdentification(prmIdentification);
    }

    public Long getIdMovie()
    {
        return atrIdPurchase.getIdMovie();
    }

    public void setIdMovie(Long prmIdMovie)
    {
        atrIdPurchase.setIdMovie(prmIdMovie);
    }

    public LocalDate getStartDate()
    {
        return atrIdPurchase.getStartDate();
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrIdPurchase.setStartDate(prmStartDate);
    }

    public LocalTime getStartTime()
    {
        return atrIdPurchase.getStartTime();
    }

    public void setStartTime(LocalTime prmStartTime)
    {
        atrIdPurchase.setStartTime(prmStartTime);
    }

    public LocalTime getEndTime()
    {
        return atrIdPurchase.getEndTime();
    }

    public void setEndTime(LocalTime prmEndTime)
    {
        atrIdPurchase.setEndTime(prmEndTime);
    }

    public Integer getCinemaRoom()
    {
        return atrIdPurchase.getCinemaRoom();
    }

    public void setCinemaRoom(Integer prmCinemaRoom)
    {
        atrIdPurchase.setCinemaRoom(prmCinemaRoom);
    }

    public Character getRow()
    {
        return atrIdPurchase.getRow();
    }

    public void setRow(Character prmRow)
    {
        atrIdPurchase.setRow(prmRow);
    }

    public Integer getNumColumn()
    {
        return atrIdPurchase.getNumColumn();
    }

    public void setNumColumn(Integer prmNumColumn)
    {
        atrIdPurchase.setNumColumn(prmNumColumn);
    }

    public String getReferenceId()
    {
        return atrIdPurchase.getReferenceId();
    }

    public void setReferenceId(String prmReferenceId)
    {
        atrIdPurchase.setReferenceId(prmReferenceId);
    }
    
    public Long getPaymentId()
    {
        return atrPaymentId;
    }

    public void setPaymentId(Long prmPaymentId)
    {
        atrPaymentId = prmPaymentId;
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
        JSON objJson = atrIdPurchase.toJSON();
        objJson.add("paymentId", atrPaymentId);
        objJson.add("value", atrValue);
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception 
    {
        return this.toJSON().equals(prmJson);
    }
}
