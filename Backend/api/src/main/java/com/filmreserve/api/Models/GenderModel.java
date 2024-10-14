package com.filmreserve.api.Models;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 *  @author javiersolanop
 */
@Table
@Entity(name = "gender")
public class GenderModel implements Serializable, iJSON {

    @EmbeddedId
    private GenderModelPK atrIdGender;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    @MapsId("atrIdMovie")
    private MovieModel atrMovie;

    public GenderModel(){}

    public GenderModelPK getIdGender()
    {
        return atrIdGender;
    }

    public void setIdGender(GenderModelPK prmGenderModelPK)
    {
        atrIdGender = prmGenderModelPK;
    }

    public void setMovie(MovieModel prmMovie)
    {
        atrMovie = prmMovie;
    }

    @Override
    public JSON toJSON() throws Exception {
        JSON objJson = new JSON();
        objJson.add("idGender", atrIdGender.toJSON());
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
