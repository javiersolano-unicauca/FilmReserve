package com.filmreserve.api.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.CinemaRoomValidation;
import com.filmreserve.api.Dao.iCinemaRoomDao;
import com.filmreserve.api.Models.CinemaRoomModel;
import com.filmreserve.api.Services.iCinemaRoomService;

/**
 * @author javiersolanop
 */
@Service
public class CinemaRoomServiceImp implements iCinemaRoomService {

    @Autowired
    iCinemaRoomDao cinemaRoomDao;

    @Override
    public CinemaRoomModel getCinemaRoomModel(Long prmIdCinemaRoom) throws Exception 
    {
        return cinemaRoomDao.findById(prmIdCinemaRoom).orElse(null);
    }

    @Override
    public JSON getCinemaRoom(Long prmIdCinemaRoom) throws Exception 
    {
        CinemaRoomModel objCinemaRoom = getCinemaRoomModel(prmIdCinemaRoom);

        ServiceResponseException.throwException(
            (objCinemaRoom == null),
            "getCinemaRoom",
            "No existe la sala con identificacion " + prmIdCinemaRoom
        );

        JSON objResponse = new JSON();
        objResponse.add("getCinemaRoom", true);
        objResponse.add("user", objCinemaRoom.toJSON());
        return objResponse;
    }

    @Override
    public JSON save(CinemaRoomModel prmCinemaRoom) throws Exception 
    {
        ServiceResponseException.throwException(
            cinemaRoomDao.existsById(prmCinemaRoom.getIdCinemaRoom()),
            "save",
            "Ya existe esa sala en el sistema"
        );

        try{ CinemaRoomValidation.validate(prmCinemaRoom); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "save", 
                e.getMessage()
        ); }

        cinemaRoomDao.save(prmCinemaRoom);

        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON delete(Long prmIdCinemaRoom) throws Exception 
    {
        ServiceResponseException.throwException(
            !cinemaRoomDao.existsById(prmIdCinemaRoom),
            "delete",
            "No existe la sala con identificacion " + prmIdCinemaRoom
        );

        cinemaRoomDao.deleteById(prmIdCinemaRoom);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }
}
