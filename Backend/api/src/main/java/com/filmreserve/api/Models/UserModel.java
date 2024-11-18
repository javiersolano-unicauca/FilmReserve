package com.filmreserve.api.Models;

import java.io.Serializable;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 *  @author javiersolanop
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Table
@Entity(name = "user")
public class UserModel implements Serializable, iJSON {

    @Id
    @Column(name = "identification")
    private Long atrIdentification;
    
    @Column(name = "first_name")
    private String atrFirstName;

    @Column(name = "second_name", nullable = true)
    private String atrSecondName;

    @Column(name = "first_surname")
    private String atrFirstSurname;

    @Column(name = "second_surname", nullable = true)
    private String atrSecondSurname;

    @Column(name = "password")
    private String atrPassword;

    @Column(name = "administrator")
    private Boolean atrAdministrator;

    @Column(name = "avatar")
    private String atrAvatar;

    public UserModel(){}

    public Long getIdentification()
    {
        return atrIdentification;
    }

    public void setIdentification(Long prmIdentification)
    {
        atrIdentification = prmIdentification;
    }

    public String getFirstName()
    {
        return atrFirstName;
    }

    public void setFirstName(String prmFirstName)
    {
        atrFirstName = prmFirstName;
    }

    public String getSecondName()
    {
        return atrSecondName;
    }

    public void setSecondName(String prmSecondName)
    {
        atrSecondName = prmSecondName;
    }

    public String getFirstSurname()
    {
        return atrFirstSurname;
    }

    public void setFirstSurname(String prmFirstSurname)
    {
        atrFirstSurname = prmFirstSurname;
    }

    public String getSecondSurname()
    {
        return atrSecondSurname;
    }

    public void setSecondSurname(String prmSecondSurname)
    {
        atrSecondSurname = prmSecondSurname;
    }

    public String getPassword()
    {
        return atrPassword;
    }

    public void setPassword(String prmPassword)
    {
        atrPassword = prmPassword;
    }

    public Boolean getAdministrator()
    {
        return atrAdministrator;
    }

    public void setAdministrator(Boolean prmAdministrator)
    {
        atrAdministrator = prmAdministrator;
    }

    public String getAvatar()
    {
        return atrAvatar;
    }

    public void setAvatar(String prmAvatar)
    {
        atrAvatar = prmAvatar;
    }

    /**
     *  Metodo para obtener una instancia de 'JSON' con la 
     *  informacion del usuario
     *  @see JSON
     */
    @Override
    public JSON toJSON() throws Exception
    {
        JSON objJson = new JSON();
        objJson.add("identification", atrIdentification);
        objJson.add("firstName", atrFirstName);
        objJson.add("secondName", (atrSecondName != null) ? atrSecondName : "null");
        objJson.add("firstSurname", atrFirstSurname);
        objJson.add("secondSurname", (atrSecondSurname != null) ? atrSecondSurname : "null");
        objJson.add("role", "Administrador");
        objJson.add("avatar", "http://localhost:8001/api/avatars/" + atrAvatar);
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}