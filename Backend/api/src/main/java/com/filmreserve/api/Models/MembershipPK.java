package com.filmreserve.api.Models;

import java.time.LocalDate;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *  @author javiersolanop
 */
@Embeddable
public class MembershipPK implements iJSON {

    @Column(name = "identification")
    private Long atrIdentification;

    @Column(name = "start_date")
    private LocalDate atrStartDate;

    public MembershipPK(){}

    public MembershipPK(Long prmIdentification, LocalDate prmStartDate)
    {
        atrIdentification = prmIdentification;
        atrStartDate = prmStartDate;
    }

    public Long getIdentification()
    {
        return atrIdentification;
    }

    public void setIdentification(Long prmIdentification)
    {
        atrIdentification = prmIdentification;
    }

    public LocalDate getStartDate()
    {
        return atrStartDate;
    }

    public void setStartDate(LocalDate prmStartDate)
    {
        atrStartDate = prmStartDate;
    }

    @Override
    public JSON toJSON() throws Exception {
        JSON objJson = new JSON();
        objJson.add("identification", atrIdentification);
        objJson.add("startDate", atrStartDate.toString());
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
