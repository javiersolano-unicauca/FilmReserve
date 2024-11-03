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

    public MembershipModel(){}

    public Long getIdentification()
    {
        return atrIdMembership.getIdentification();
    }

    public LocalDate getStartDate()
    {
        return atrIdMembership.getStartDate();
    }

    public void setIdMembership(MembershipPK prmMembershipPK)
    {
        atrIdMembership = prmMembershipPK;
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
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
