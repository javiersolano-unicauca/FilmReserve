package com.filmreserve.api.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.MovieModel;

/**
 * @author javiersolanop
 */
public interface iMovieService {

    /**
     *  Metodo para obtener el nombre de una pelicula a partir de su id
     * 
     * @param prmIdMovie Recibe el id de la pelicula
     * 
     * @return El nombre si existe la pelicula. De lo contrario null
     */
    public String getName(Long prmIdMovie);

    /**
     *  Metodo para obtener una pelicula por 'idMovie'
     *  
     *  @param prmIdMovie Recibe la identificacion
     *  
     *  @return Una instancia del modelo con la informacion de la pelicula si existe.
     *          De lo contrario null
     */
    public MovieModel getMovieModel(Long prmIdMovie) throws Exception;

    /**
     *  Metodo para obtener todas las peliculas
     * 
     *  @return La lista con peliculaso
     * 
     * @throws Exception Si no hay peliculas
     */
    public List<MovieModel> all() throws Exception;

    /**
     *  Metodo para obtener una pelicula por 'IdMovie'
     *  
     *  @param prmIdMovie Recibe la identificacion
     *  
     *  @return Una instancia JSON con la clave 'getMovie' en 'true' y con la
     *          informacion de la pelicula si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getMovie(Long prmIdMovie) throws Exception;

    /**
     *  Metodo para guardar una pelicula
     * 
     *  @param prmMovie Recibe los datos de la pelicula
     *  @param prmGenders Recibe los generos de la pelicula.
     * 
     *  Ejemplo del patron permitido para los generos:
     *  <blockquote>
     *      <pre> [horror, drama, suspense] </pre>
     *  </blockquote>
     * 
     *  @param prmPosterImage Recibe la imagen del poster
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede guardar la pelicula
     */
    public JSON save(MovieModel prmMovie, String prmGenders, MultipartFile prmPosterImage) throws Exception;

    /**
     *  Metodo para actualizar el poster de una pelicula
     *  
     *  @param prmIdMovie Recibe la identificacion
     *  @param prmPosterImage Recibe la nueva imagen del poster
     * 
     *  @return Una instancia JSON con la clave 'update' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede actualizar el poster
     */
    public JSON updatePosterImage(Long prmIdMovie, MultipartFile prmPosterImage) throws Exception;

    /**
     *  Metodo para eliminar una pelicula 
     * 
     *  @param prmIdMovie Recibe la identificacion
     * 
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON delete(Long prmIdMovie) throws Exception;
}
