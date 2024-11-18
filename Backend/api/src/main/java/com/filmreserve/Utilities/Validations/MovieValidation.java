package com.filmreserve.Utilities.Validations;

import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Exceptions.ChainOfCharacterException;
import com.filmreserve.Utilities.ModelsException.MovieException;
import com.filmreserve.api.Models.MovieModel;

/**
 *  Clase para las validaciones de las peliculas
 *  @see MovieModel
 * 
 *  @author javiersolanop
 */
public class MovieValidation {

    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmMovie Recibe la referencia de la pelicula
     *  @param prmGenders Recibe los generos de la pelicula.
     * 
     *  Ejemplo del patron permitido para los generos:
     *  <blockquote>
     *      <pre> [horror, drama, suspense] </pre>
     *  </blockquote>
     * 
     *  @param prmPosterImage Recibe la imagen del poster
     * 
     *  @throws MovieException Cuando los campos no son validos en su totalidad
     */
    public static void validate(MovieModel prmMovie, String prmGenders, MultipartFile prmPosterImage) throws MovieException
    {
        validateId(prmMovie);
        validateName(prmMovie);
        validateSypnosis(prmMovie);
        validatePosterImage(prmMovie, prmPosterImage);
        validateGenders(prmGenders);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws MovieException Si el campo esta vacio
     */
    private static void validateNull(Object prmField, int prmFieldType) throws MovieException
    {
        MovieException.throwException(
            prmField == null, 
            prmFieldType,
            new Exception("No debe estar nulo")
        );
    }

    /**
     *  Metodo para validar si un campo esta vacio
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws MovieException Si el campo esta vacio
     */
    private static void validateEmpty(String prmField, int prmFieldType) throws MovieException
    {
        MovieException.throwException(
            prmField.isEmpty(), 
            prmFieldType,
            new Exception("No debe estar vacio")
        );
    }

    /**
     *  @param prmMovie Recibe la referencia de la pelicula
     * 
     *  @throws MovieException Si el campo no es valido
     */
    public static void validateId(MovieModel prmMovie) throws MovieException 
    {
        validateNull(prmMovie.getIdMovie(), MovieException.ID_MOVIE);

        MovieException.throwException(
            (prmMovie.getIdMovie() <= 0), 
            MovieException.ID_MOVIE,
            new Exception("Debe ser positivo")
        );
    }

    /**
     *  @param prmMovie Recibe la referencia de la pelicula
     * 
     *  @throws MovieException Si el campo no es valido
     */
    public static void validateName(MovieModel prmMovie) throws MovieException
    {
        validateNull(prmMovie.getName(), MovieException.NAME);
        validateEmpty(prmMovie.getName(), MovieException.NAME);

        MovieException.throwException(
            prmMovie.getName().contains("<script>"),
            MovieException.NAME,
            new Exception("No debe ingresar instrucciones!")
        );
    }

    /**
     *  @param prmMovie Recibe la referencia de la pelicula
     * 
     *  @throws MovieException Si el campo no es valido
     */
    public static void validateSypnosis(MovieModel prmMovie) throws MovieException
    {
        validateNull(prmMovie.getSypnosis(), MovieException.SYPNOSIS);
        validateEmpty(prmMovie.getSypnosis(), MovieException.SYPNOSIS);

        MovieException.throwException(
            (prmMovie.getSypnosis().length() > 300),
            MovieException.SYPNOSIS,
            new Exception("No debe superar los 300 caracteres")
        );

        MovieException.throwException(
            prmMovie.getSypnosis().contains("<script>"),
            MovieException.SYPNOSIS,
            new Exception("No debe ingresar instrucciones!")
        );
    }

    /**
     *  @param prmMovie Recibe la referencia de la pelicula
     *  @param prmPosterImage Recibe la imagen del poster
     * 
     *  @throws MovieException Si el campo no es valido
     */
    public static void validatePosterImage(MovieModel prmMovie, MultipartFile prmPosterImage) throws MovieException
    {
        MovieException.throwException(
            (prmMovie.getPosterImage() != null),
            MovieException.POSTER_IMAGE,
            new Exception("Debe ser una imagen!")
        );
        validateNull(prmPosterImage, MovieException.POSTER_IMAGE);

        String varFileName = prmPosterImage.getOriginalFilename();

        MovieException.throwException(
            (!varFileName.endsWith(".jpeg")
            && !varFileName.endsWith(".jpg")),
            MovieException.POSTER_IMAGE,
            new Exception("Debe ser de tipo 'jpeg' o 'jpg'")
        );

        MovieException.throwException(
            prmPosterImage.getSize() > 10000000,
            MovieException.POSTER_IMAGE,
            new Exception("No debe superar los 10MB")
        );
    }

    /**
     *  @param prmMovie Recibe los generos
     * 
     *  @throws MovieException Si el campo no es valido
     */
    public static void validateGenders(String prmGenders) throws MovieException
    {
        validateNull(prmGenders, MovieException.GENDERS);
        validateEmpty(prmGenders, MovieException.GENDERS);

        try{ 

            String[] arrGenders = ChainOfCharacter.listToArray(prmGenders);

            MovieException.throwException(
                ((arrGenders == null)
                || (arrGenders.length > 10)),
                MovieException.GENDERS,
                new Exception("No debe estar vacio y tampoco ser superior a 10 generos")
            );  

        }catch(ChainOfCharacterException e)
        {
            MovieException.throwException(MovieException.GENDERS, e);
        }
    }
}
