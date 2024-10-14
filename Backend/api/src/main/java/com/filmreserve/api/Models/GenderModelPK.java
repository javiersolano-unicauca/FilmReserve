package com.filmreserve.api.Models;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *  @author javiersolanop
 */
@Embeddable
public class GenderModelPK implements iJSON {

    @Column(name = "id_movie")
    private Long atrIdMovie;

    @Column(name = "name")
    private String atrName;

    public GenderModelPK(){}

    public GenderModelPK(Long prmIdMovie, String prmName)
    {
        atrIdMovie = prmIdMovie;
        atrName = prmName;
    }

    public Long getIdMovie()
    {
        return atrIdMovie;
    }

    public void setIdMovie(Long prmIdMovie)
    {
        atrIdMovie = prmIdMovie;
    }

    public String getName()
    {
        return atrName;
    }

    public void setName(String prmName)
    {
        atrName = prmName;
    }

    @Override
    public JSON toJSON() throws Exception
    {
        JSON objJson = new JSON();
        objJson.add("idMovie", atrIdMovie);
        objJson.add("name", atrName);
        return objJson;
    }

    @Override
    public boolean equals(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
