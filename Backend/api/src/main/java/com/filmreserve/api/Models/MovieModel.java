package com.filmreserve.api.Models;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author javiersolanop
 */
@Table
@Entity(name = "movie")
public class MovieModel implements Serializable, iJSON  {

    @Id
    @Column(name = "id_movie")
    private Long atrIdMovie;

    @Column(name = "name")
    private String atrName;

    @Column(name = "sypnosis")
    private String atrSypnosis;

    @Column(name = "poster_image")
    private String atrPosterImage;

    @OneToMany(mappedBy = "atrMovie")
    private List<GenderModel> listGenders;

    public MovieModel(){}

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

    public String getSypnosis()
    {
        return atrSypnosis;
    }

    public void setSypnosis(String prmSypnosis)
    {
        atrSypnosis = prmSypnosis;
    }

    public String getPosterImage()
    {
        return atrPosterImage;
    }

    public void setPosterImage(String prmPosterImage)
    {
        atrPosterImage = prmPosterImage;
    }

    public String getPoster()
    {
        return "http://localhost:8001/api/posters/" + atrPosterImage;
    }

    public List<GenderModel> getGenders()
    {
        return listGenders;
    }

    public void setGenders(List<GenderModel> prmGenders)
    {
        listGenders = prmGenders;
    }

    /**
     *  Metodo para obtener una instancia de 'JSON' con la 
     *  informacion de la pelicula
     *  @see JSON
     */
    @Override
    public JSON toJSON() throws Exception
    {
        JSON objJson = new JSON();
        objJson.add("idMovie", atrIdMovie);
        objJson.add("name", atrName);
        objJson.add("sypnosis", atrSypnosis);
        objJson.add(
            "poster", 
            "http://localhost:8001/api/posters/" + atrPosterImage
        );
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
