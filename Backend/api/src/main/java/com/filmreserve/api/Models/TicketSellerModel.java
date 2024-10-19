package com.filmreserve.api.Models;

import com.filmreserve.Utilities.Arrays.JSON.JSON;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 *  @author javiersolanop
 */
@Table
@Entity(name = "ticket_seller")
@PrimaryKeyJoinColumn(name = "identification")
public class TicketSellerModel extends UserModel {

    @Column(name = "turn")
    private String atrTurn;

    public TicketSellerModel(){}

    public String getTurn()
    {
        return atrTurn;
    }

    public void setTurn(String prmTurn)
    {
        atrTurn = prmTurn;
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
        objJson.add("turn", atrTurn);
        objJson.add("avatar", getAvatar());
        objJson.add("role", "Taquillero");
        return objJson;
    }
}
