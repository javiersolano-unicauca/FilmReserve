package com.filmreserve.api.Services.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedList;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.SeatValidation;
import com.filmreserve.api.Dao.iSeatDao;
import com.filmreserve.api.Models.SeatModel;
import com.filmreserve.api.Models.SeatPK;
import com.filmreserve.api.Services.iSeatService;

/**
 * @author javiersolanop
 */
@Service
public class SeatServiceImp implements iSeatService {

    @Autowired
    iSeatDao seatDao;

    @Override
    public SeatModel getSeatModel(Integer prmCinemaRoom, Character prmRow, Integer prmNumColumn) throws Exception 
    {
        return seatDao.findById(new SeatPK(prmCinemaRoom, prmRow, prmNumColumn)).orElse(null);
    }

    @Override
    public JSON getSeat(Integer prmCinemaRoom, Character prmRow, Integer prmNumColumn) throws Exception 
    {
        SeatModel objSeat = getSeatModel(prmCinemaRoom, prmRow, prmNumColumn);

        ServiceResponseException.throwException(
            (objSeat == null),
            "getSeat",
            "No existe el asiento con fila " + prmRow + 
            ", columna " + prmNumColumn + " y sala " + prmCinemaRoom
        );

        JSON objResponse = new JSON();
        objResponse.add("getSeat", true);
        objResponse.add("user", objSeat.toJSON());
        return objResponse;
    }

    @Override
    public LinkedListJSON getSeatsOfCinemaRoom(Integer prmCinemaRoom) throws Exception {
        
        List<SeatModel> listSeats = seatDao.findByCinemaRoom(prmCinemaRoom);

        ServiceResponseException.throwException(
            listSeats.isEmpty(),
            "getCinemaRoom", 
            "No existen asientos registrados para la sala " + prmCinemaRoom
        );

        int varSize = listSeats.size(), varIndex = 0;
        boolean varFlag = true;
        char varRow;
        SeatModel objSeat;
        LinkedListJSON listSeatsJson = new LinkedListJSON(), listSeatContent;
        JSON objSeatJson;
        
        while(varFlag)
        {
            objSeat = listSeats.get(varIndex);
            varRow = objSeat.getRow();

            objSeatJson = new JSON();
            objSeatJson.add("row", varRow);

            listSeatContent = new LinkedListJSON();

            while(objSeat.getRow() == varRow)
            {
                listSeatContent.add(objSeat);

                if((varIndex + 1) < varSize) 
                    objSeat = listSeats.get(++varIndex);
                else{
                    varFlag = false;
                    break;
                }
            }
            objSeatJson.add("columns", listSeatContent);
            listSeatsJson.add(objSeatJson);
        }
        return listSeatsJson;
    }

    @Override 
    public JSON save(SeatPK prmSeatPK) throws Exception 
    {
        ServiceResponseException.throwException(
            seatDao.existsById(prmSeatPK),
            "save",
            "Ya existe ese asiento en el sistema"
        );

        try{ SeatValidation.validate(prmSeatPK); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "save", 
                e.getMessage()
        ); }

        SeatModel objSeat = new SeatModel();
        objSeat.setIdSeat(prmSeatPK);
        objSeat.setReserved(false);
        seatDao.save(objSeat);

        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON delete(Integer prmCinemaRoom, Character prmRow, Integer prmNumColumn) throws Exception 
    {
        SeatPK objSeatPK = new SeatPK(prmCinemaRoom, prmRow, prmNumColumn);

        ServiceResponseException.throwException(
            !seatDao.existsById(objSeatPK),
            "delete",
            "No existe el asiento con fila " + prmRow + 
            ", columna " + prmNumColumn + " y sala " + prmCinemaRoom
        );

        seatDao.deleteById(objSeatPK);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }
}
