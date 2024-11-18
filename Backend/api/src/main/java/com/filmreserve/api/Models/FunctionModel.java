package com.filmreserve.api.Models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;

/**
 * @author javiersolanop
 */
@Table
@Entity(name = "function")
public class FunctionModel implements Serializable, iJSON  {

    @EmbeddedId
    private FunctionPK atrIdFunction;

    @Column(name = "active")
    private Boolean atrActive;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    @MapsId("atrIdMovie")
    private MovieModel atrMovie;

    public FunctionModel(){ atrIdFunction = new FunctionPK(); }

    public void setIdFunction(FunctionPK prmFunctionPK)
    {
        atrIdFunction = prmFunctionPK;
    }

    public LocalDate getStartDate()
    {
        return atrIdFunction.getStartDate();
    }

    public LocalTime getStartTime()
    {
        return atrIdFunction.getStartTime();
    }

    public LocalTime getEndTime()
    {
        return atrIdFunction.getEndTime();
    }

    public Boolean getActive()
    {
        return atrActive;
    }

    public void setActive(Boolean prmActive)
    {
        atrActive = prmActive;
    }

    public MovieModel getMovie()
    {
        return atrMovie;
    }

    public void setMovie(MovieModel prmMovie)
    {
        atrMovie = prmMovie;
    }

    public void setMovie(Long prmIdMovie)
    {
        atrMovie = new MovieModel();
        atrMovie.setIdMovie(prmIdMovie);
    }

    /**
     *  Metodo para obtener una instancia de 'JSON' con la 
     *  informacion de la funcion
     *  @see JSON
     */
    @Override
    public JSON toJSON() throws Exception
    {
        JSON objJson = atrIdFunction.toJSON();
        objJson.add("active", atrActive);
        objJson.add("movie", atrMovie.toJSON());
        return objJson;
    }

    @Override
    public boolean equalsJSON(JSON prmJson) throws Exception {
        return this.toJSON().equals(prmJson);
    }
}
