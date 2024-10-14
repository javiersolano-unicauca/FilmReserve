package com.filmreserve.api.Services.Implementation;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Exceptions.ChainOfCharacterException;
import com.filmreserve.Utilities.Files.File;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.MovieValidation;
import com.filmreserve.api.Dao.iGenderDao;
import com.filmreserve.api.Dao.iMovieDao;
import com.filmreserve.api.Models.GenderModel;
import com.filmreserve.api.Models.GenderModelPK;
import com.filmreserve.api.Models.MovieModel;
import com.filmreserve.api.Services.iMovieService;

/**
 * @author javiersolanop
 */
@Service
public class MovieServiceImp  implements iMovieService{

    @Autowired
    iMovieDao movieDao;

    @Autowired
    iGenderDao genderDao;

    @Override
    public MovieModel getMovieModel(Long prmIdMovie) throws Exception 
    {
        MovieModel objMovie = movieDao.findById(prmIdMovie).orElse(null);

        ServiceResponseException.throwException(
            (objMovie == null),
            "getMovie",
            "No existe la pelicula con identificacion " + prmIdMovie
        );
        return objMovie;
    }

    @Override
    public List<MovieModel> all() throws Exception 
    {
        List<MovieModel> objList = (List<MovieModel>) movieDao.findAll();
        
        ServiceResponseException.throwException(
            objList.isEmpty(),
            "getMovies",
            "No existen peliculas en el sistema"
        );  

        return objList;
    }

    @Override
    public JSON getMovie(Long prmIdMovie) throws Exception 
    { 
        MovieModel objMovie = getMovieModel(prmIdMovie);

        ServiceResponseException.throwException(
            (objMovie == null),
            "getMovie",
            "No existe la pelicula con identificacion " + prmIdMovie
        );

        JSON objResponse = new JSON();
        objResponse.add("getMovie", true);
        objResponse.add("user", objMovie.toJSON());
        return objResponse;
    }

    /**
     *  Metodo para obtener una lista de generos a partir de una cadena
     * 
     *  @param prmMovie Recibe la referencia a la pelicula
     *  @param prmGenders Recibe la cadena con los generos
     * 
     *  @return La lista de generos
     * 
     *  @throws ChainOfCharacterException si el patron no es permitido en la cadena
     */
    private List<GenderModel> toList(MovieModel prmMovie,  String prmGenders) throws ChainOfCharacterException
    {
        List<GenderModel> listGenders = new LinkedList<>();
        String[] arrGenders = ChainOfCharacter.toArray(prmGenders);
        GenderModel objGender;
        Long varIdMovie = prmMovie.getIdMovie();

        for(String varGender: arrGenders)
        {   
            objGender = new GenderModel();
            objGender.setIdGender(new GenderModelPK(varIdMovie, varGender));
            objGender.setMovie(prmMovie);
            listGenders.add(objGender);
        }
        return listGenders;
    }

    @Override
    public JSON save(MovieModel prmMovie, String prmGenders, MultipartFile prmPosterImage) throws Exception 
    {
        ServiceResponseException.throwException(
            movieDao.existsById(prmMovie.getIdMovie()),
            "save",
            "Ya existe esa pelicula en el sistema"
        );

        try{ MovieValidation.validate(prmMovie, prmGenders, prmPosterImage); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "save", 
                e.getMessage()
        ); }

        String varFilename = ChainOfCharacter.substring(
            prmPosterImage.getOriginalFilename(),'.'
        );

        new File("posters").exportJpeg(
            varFilename,
            prmPosterImage.getBytes()
        );
        
        prmMovie.setPosterImage(varFilename);
        movieDao.save(prmMovie);
        genderDao.saveAll(toList(prmMovie, prmGenders));

        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON updatePosterImage(Long prmIdMovie, MultipartFile prmPosterImage) throws Exception 
    { 
        MovieModel objMovie = movieDao.findById(prmIdMovie).orElse(null);

        ServiceResponseException.throwException(
            (objMovie == null),
            "update",
            "No existe la pelicula con identificacion " + prmIdMovie
        );

        String varFilename = objMovie.getPosterImage();
        objMovie.setPosterImage(null);

        try{ MovieValidation.validatePosterImage(objMovie, prmPosterImage); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "update", 
                e.getMessage()
        ); }

        File objFile = new File("posters");
        objFile.removeJpeg(varFilename);

        varFilename = ChainOfCharacter.substring(
            prmPosterImage.getOriginalFilename(),'.'
        );

        objFile.exportJpeg(
            varFilename,
            prmPosterImage.getBytes()
        );
        
        objMovie.setPosterImage(varFilename);
        movieDao.save(objMovie);

        JSON objJson = new JSON();
        objJson.add("update", true);
        return objJson;
    }

    @Override
    public JSON delete(Long prmIdMovie) throws Exception 
    {
        MovieModel objMovie = movieDao.findById(prmIdMovie).orElse(null);

        ServiceResponseException.throwException(
            (objMovie == null),
            "delete",
            "No existe la pelicula con identificacion " + prmIdMovie
        );

        new File("posters").removeJpeg(objMovie.getPosterImage());
        movieDao.deleteById(prmIdMovie);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }
}
