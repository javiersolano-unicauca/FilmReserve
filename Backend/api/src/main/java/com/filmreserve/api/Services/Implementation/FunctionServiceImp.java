package com.filmreserve.api.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.FunctionValidation;
import com.filmreserve.api.Dao.iFunctionDao;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.FunctionPK;
import com.filmreserve.api.Services.iFunctionService;
import com.filmreserve.api.Services.iSeatService;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;

/**
 *  @author javiersolanop
 */
@Service
public class FunctionServiceImp implements iFunctionService {

    @Autowired
    iFunctionDao functionDao;

    @Autowired
    iSeatService seatService;

    @Override
    public FunctionModel getFunctionModel(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime
    ) throws Exception 
    {
        return functionDao.find(prmIdMovie, prmStartDate, prmStartTime);
    }

    @Override
    public boolean exists(Long prmIdMovie, LocalDate prmStartDate, LocalTime prmStartTime) throws Exception 
    {
        return (getFunctionModel(prmIdMovie, prmStartDate, prmStartTime) != null);
    }

    @Override
    public boolean existsActive(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime
    ) throws Exception {
        return (functionDao.findByActive(prmIdMovie, prmStartDate, prmStartTime, true) != null);
    }

    @Override
    public JSON getFunction(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime
    ) throws Exception 
    {
        FunctionModel objFunction = getFunctionModel(prmIdMovie, prmStartDate, prmStartTime);

        ServiceResponseException.throwException(
            (objFunction == null),
            "getFunction",
            "No existe la funcion con identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate.toString()
            + " y hora de inicio " + prmStartTime.toString()
        );

        JSON objJson = new JSON();
        objJson.add("getFunction", true);
        objJson.add("function", objFunction.toJSON());
        return objJson;
    }

    @Override
    public List<FunctionModel> all() throws Exception 
    {
        return (List<FunctionModel>) functionDao.findAll();
    }

    @Override
    public JSON save(FunctionPK prmFunctionPK) throws Exception 
    {
        ServiceResponseException.throwException(
            (functionDao.find(
                prmFunctionPK.getIdMovie(),
                prmFunctionPK.getStartDate(),
                prmFunctionPK.getStartTime()
            ) != null),
            "save",
            "Ya existe la funcion con identificacion de pelicula " + prmFunctionPK.getIdMovie()
            + ", fecha de inicio " + prmFunctionPK.getStartDate().toString()
            + " y hora de inicio " + prmFunctionPK.getStartTime().toString()
        );

        try{ FunctionValidation.validate(prmFunctionPK); }
        catch(Exception e){
            ServiceResponseException.throwException(
            "save",
            e.getMessage()
        );}

        FunctionModel objFunction = new FunctionModel();
        objFunction.setIdFunction(prmFunctionPK);
        objFunction.setMovie(prmFunctionPK.getIdMovie());
        objFunction.setActive(true);

        functionDao.save(objFunction);

        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON functionEnd(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime
    ) throws Exception  
    {
        FunctionModel objFunction = getFunctionModel(prmIdMovie, prmStartDate, prmStartTime);

        ServiceResponseException.throwException(
            (objFunction == null),
            "functionEnd",
            "No existe la funcion con la identificacion de pelicula " + prmIdMovie 
            + ", fecha de inicio " + prmStartDate.toString()
            + " y hora de inicio " + prmStartTime.toString()
        );

        objFunction.setActive(false);
        functionDao.save(objFunction);
        
        JSON objJson = new JSON();
        objJson.add("functionEnd", true);
        return objJson;
    }

    @Override
    public JSON delete(
        Long prmIdMovie,  
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception 
    {
        FunctionModel objFunction = getFunctionModel(prmIdMovie, prmStartDate, prmStartTime);

        ServiceResponseException.throwException(
            (objFunction == null),
            "delete",
            "No existe la funcion con la identificacion de pelicula " + 
            + prmIdMovie + ", fecha de inicio " + prmStartDate.toString()
            + " y hora de inicio " + prmStartTime.toString()
        );

        functionDao.delete(objFunction);
        
        JSON objJson = new JSON();
        objJson.add("delete", true);
        return objJson;
    }
}
