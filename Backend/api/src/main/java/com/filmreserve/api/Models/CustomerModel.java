package com.filmreserve.api.Models;

import java.io.Serializable;
import java.time.LocalDate;

import com.filmreserve.Libraries.Arrays.JSON.JSON;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 *  @author javiersolanop
 */
@Table
@Entity(name = "customer")
@PrimaryKeyJoinColumn(name = "identification")
public class CustomerModel extends UserModel implements Serializable{

    @Column(name = "day")
    private Integer atrDay;

    @Column(name = "month")
    private Integer atrMonth;

    @Column(name = "year")
    private Integer atrYear;

    @Column(name = "phone")
    private Long atrPhone;

    public CustomerModel(){}

    public Integer getDay()
    {
        return atrDay;
    }

    public void setDay(Integer prmDay)
    {
        atrDay = prmDay;
    }

    public Integer getMonth()
    {
        return atrMonth;
    }

    public void setMonth(Integer prmMonth)
    {
        atrMonth = prmMonth;
    }

    public Integer getYear()
    {
        return atrYear;
    }

    public void setYear(Integer prmYear)
    {
        atrYear = prmYear;
    }

    public Long getPhone()
    {
        return atrPhone;
    }

    public void setPhone(Long prmPhone)
    {
        atrPhone = prmPhone;
    }

    public Integer getAge()
    {
        LocalDate objDate = LocalDate.now();
        int varAge = objDate.getYear() - atrYear;
        
        if((atrMonth == objDate.getMonthValue()) 
        && (atrDay > objDate.getDayOfMonth())) varAge--;

        else if(atrMonth > objDate.getMonthValue()) varAge--;
        return varAge;
    }

    /**
     *  Metodo para obtener una instancia de 'JSON' con la 
     *  informacion del usuario
     *  @see JSON
     */
    public JSON toJSON() throws Exception
    {
        JSON objJson = new JSON();
        objJson.add("identification", getIdentification());
        objJson.add("firstName", getFirstName());
        objJson.add("secondName", (getSecondName() != null) ? getSecondName() : "null");
        objJson.add("firstSurname", getFirstSurname());
        objJson.add("secondSurname", (getSecondSurname() != null) ? getSecondSurname() : "null");
        objJson.add("day", atrDay);
        objJson.add("month", atrMonth);
        objJson.add("year", atrYear);
        objJson.add("age", getAge());
        return objJson;
    }
}
