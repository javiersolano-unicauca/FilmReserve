package com.filmreserve.api.Models;

import java.time.LocalDate;

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
@Entity(name = "membership")
public class MembershipModel implements iJSON  {

    @EmbeddedId
    private MembershipPK atrIdMembership;

    @Column(name = "end_date")
    private LocalDate atrEndDate;

    @Column(name = "active")
    private Boolean atrActive;

    @Column(name = "reference_id")
    private String atrReferenceId;

    @Column(name = "payment_id")
    private Long atrPaymentId;

    public MembershipModel(){ atrIdMembership = new MembershipPK(); }

    public MembershipModel(
        Long prmIdentification,
        LocalDate prmStartDate,
        LocalDate prmEndDate,
        Boolean prmActive,
        String prmReferenceId,
        Long prmPaymentId
    )
    {
        atrIdMembership = new MembershipPK(prmIdentification, prmStartDate);
        atrEndDate = prmEndDate;
        atrActive = prmActive;
        atrReferenceId = prmReferenceId;
        atrPaymentId = prmPaymentId;
    }

    public void setIdMembership(MembershipPK prmMembershipPK)
    {
        atrIdMembership = prmMembershipPK;
    }

    public Long getIdentification()
    {
        return atrIdMembership.getIdentification();
    }

    public void setIdentification(Long prmIdentification)
    {
        atrIdMembership.setIdentification(prmIdentification);
    }

    public LocalDate getStartDate()
    {
        return atrIdMembership.getStartDate();
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrIdMembership.setStartDate(prmStartDate);
    }

    public LocalDate getEndDate()
    {
        return atrEndDate;
    }

    public void setEndDate(LocalDate prmEndDate)
    {
        atrEndDate = prmEndDate;
    }

    public Boolean getActive()
    {
        return atrActive;
    }

    public void setActive(Boolean prmActive)
    {
        atrActive = prmActive;
    }

    public String getReferenceId()
    {
        return atrReferenceId;
    }

    public void setReferenceId(String prmReferenceId)
    {
        atrReferenceId = prmReferenceId;
    }

    public Long getPaymentId()
    {
        return atrPaymentId;
    }

    public void setPaymentId(Long prmPaymentId)
    {
        atrPaymentId = prmPaymentId;
    }

    @Override
    public JSON toJSON() throws Exception {
        JSON objJson = new JSON();
        objJson.add("identification", getIdentification());
        objJson.add("startDate", getStartDate().toString());
        objJson.add("endDate", atrEndDate.toString());
        objJson.add("active", atrActive);
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
