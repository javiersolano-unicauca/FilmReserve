package com.filmreserve.api.Services.Implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedList;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.api.Dao.iReserveDao;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.ReserveModel;
import com.filmreserve.api.Models.SeatModel;
import com.filmreserve.api.Services.iFunctionService;
import com.filmreserve.api.Services.iReserveService;
import com.filmreserve.api.Services.iSeatService;

/**
 *  @author javiersolanop
 */
@Service
public class ReserveServiceImp implements iReserveService {

    @Autowired
    iReserveDao reserveDao;

    @Autowired
    iFunctionService functionService;

    @Autowired
    iSeatService seatService;

    @Override
    public FunctionModel getFunctionModel(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    ) throws Exception 
    {
        ReserveModel objReserveModel = reserveDao.findFirstByFunctionAndCinemaRoom(prmIdMovie, prmStartDate, prmStartTime, prmCinemaRoom);

        if(objReserveModel == null) return null;

        FunctionModel objFunction = new FunctionModel();
        objFunction.setIdFunction(objReserveModel.findIdReserve().getIdFunction());
        return objFunction;
    }

    @Override
    public JSON getFunction(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        Integer prmCinemaRoom
    ) throws Exception 
    {
        ReserveModel objReserveModel = reserveDao.findFirstByFunctionAndCinemaRoom(prmIdMovie, prmStartDate, prmStartTime, prmCinemaRoom);

        ServiceResponseException.throwException(
            (objReserveModel == null),
            "getFunction",
            "No existe la reserva para la funcion con"
            + " identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate.toString()
            + ", hora de inicio " + prmStartTime.toString()
            + " y sala " + prmCinemaRoom
        );

        JSON objJson = new JSON(), objFunction = new JSON();
        objFunction.add("idMovie", prmIdMovie);
        objFunction.add("startDate", prmStartDate.toString());
        objFunction.add("startTime", prmStartTime.toString());
        objFunction.add("endTime", objReserveModel.getEndTime().toString());

        objJson.add("getFunction", true);
        objJson.add("function", objFunction);
        return objJson;
    }

    /**
     *  Metodo para enlistar los asientos a partir de la lista de reservas
     * 
     *  @param prmList Recibe la lista de reservas
     *  @param prmBegin Recibe el indice donde se empieza enlistar
     *  @param prmEnd Recibe el indice donde finaliza el enlistado
     * 
     *  @return La lista de asientos si existen las reservas. De lo contrario null
     */
    private LinkedListJSON reservesToSeats(List<ReserveModel> prmList, int prmBegin, int prmEnd) throws Exception
    {
        if(prmList.isEmpty()) return null;

        boolean varFlag = true;
        char varRow;
        ReserveModel objReserve;
        LinkedListJSON listReservesJson = new LinkedListJSON(), listSeatContent;
        JSON objReserveJson;
        
        while(varFlag)
        {
            objReserve = prmList.get(prmBegin);
            varRow = objReserve.getRow();

            objReserveJson = new JSON();
            objReserveJson.add("row", varRow);

            listSeatContent = new LinkedListJSON();

            while(objReserve.getRow() == varRow)
            {
                listSeatContent.add(objReserve);

                if((prmBegin + 1) <= prmEnd) 
                    objReserve = prmList.get(++prmBegin);
                else{
                    varFlag = false;
                    break;
                }
            }
            objReserveJson.add("columns", listSeatContent);
            listReservesJson.add(objReserveJson);
        }
        return listReservesJson;
    }

    @Override
    public LinkedListJSON getSeatsOfCinemaRoom(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime,
        Integer prmCinemaRoom
    ) throws Exception 
    {
        List<ReserveModel> listReserves = reserveDao.findAllByFunctionAndCinemaRoom(prmIdMovie, prmStartDate, prmStartTime, prmCinemaRoom);

        ServiceResponseException.throwException(
            (listReserves.isEmpty()),
            "getSeats",
            "No existe la reserva para la funcion con"
            + " identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate.toString()
            + ", hora de inicio " + prmStartTime.toString()
            + " y sala " + prmCinemaRoom
        );

        return reservesToSeats(listReserves, 0, listReserves.size() - 1);
    }

    @Override
    public LinkedListJSON getSeatsOfFunction(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime
    ) throws Exception 
    {
        List<ReserveModel> listReserves = reserveDao.findAllByFunction(prmIdMovie, prmStartDate, prmStartTime);

        ServiceResponseException.throwException(
            (listReserves.isEmpty()),
            "getSeats",
            "No existen reservas para la funcion con"
            + " identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate.toString()
            + " y hora de inicio " + prmStartTime.toString()
        );

        int varSize = listReserves.size(), varIndex = 0, varEnd = varIndex;
        Integer varCinemaRoom;
        ReserveModel objReserve;
        LinkedListJSON listReservesJson = new LinkedListJSON();
        JSON objReserveJson;
        
        while(varEnd < varSize)
        {
            objReserve = listReserves.get(varIndex);
            varCinemaRoom = objReserve.getCinemaRoom();
            
            while(((varEnd + 1) < varSize) && (listReserves.get(varEnd + 1).getCinemaRoom() == varCinemaRoom)) varEnd++;

            objReserveJson = new JSON();
            objReserveJson.add("cinemaRoom", varCinemaRoom);
            objReserveJson.add("seats", reservesToSeats(listReserves, varIndex, varEnd));

            listReservesJson.add(objReserveJson);
            varIndex = ++varEnd;
        }
        return listReservesJson;
    }

    /**
     *  Metodo para generar las reservas de los asientos de una funcion por sala
     * 
     *  @param prmIdMovie    Recibe el id de la pelicula
     *  @param prmStartDate  Recibe la fecha de inicio
     *  @param prmStartTime  Recibe la hora de inicio
     *  @param prmEndTime    Recibe la hora fin
     *  @param prmCinemaRoom Recibe la sala
     *  @param prmListSeats  Recibe la lista de asientos 
     * 
     *  @return La lista con las reservas si hay asientos. De lo contrario vacia
     */
    private List<ReserveModel> seatToReserve(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        List<SeatModel> prmListSeats
    ) throws Exception
    {
        List<ReserveModel> listReserves = new ArrayList<>();

        if(prmListSeats.isEmpty()) return listReserves;

        for(SeatModel objSeat: prmListSeats)
        {
            listReserves.add(
                new ReserveModel(
                    prmIdMovie, 
                    prmStartDate, 
                    prmStartTime, 
                    prmEndTime, 
                    prmCinemaRoom, 
                    objSeat.getRow(), 
                    objSeat.getNumColumn(), 
                    false
            ));
        }
        return listReserves;
    }

    @Override
    public JSON save(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        LocalTime prmEndTime,
        Integer prmCinemaRoom
    ) throws Exception 
    {
        ServiceResponseException.throwException(
            (!seatService.existsCinemaRoom(prmCinemaRoom)),
            "save",
            "No existe la sala " + prmCinemaRoom
        );

        ServiceResponseException.throwException(
            (!functionService.existsActive(prmIdMovie, prmStartDate, prmStartTime)),
            "save",
            "No existe una funcion activa con identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate.toString()
            + " y hora de inicio " + prmStartTime.toString()
        );

        ReserveModel objReserve = reserveDao.findByCinemaRoom(prmCinemaRoom, prmStartDate, prmStartTime, prmEndTime);

        ServiceResponseException.throwException(
            (objReserve != null),
            "save",
            "Ya se encuentra reservada la sala " + prmCinemaRoom
            + " en la fecha de inicio " + prmStartDate.toString()
            + " y las horas " + ((objReserve != null) ? objReserve.getStartTime().toString() : "")
            + " - " + ((objReserve != null) ? objReserve.getEndTime().toString() : "")
        );

        reserveDao.saveAll(seatToReserve(
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmEndTime, 
            prmCinemaRoom, 
            seatService.getSeatsModelOfCinemaRoom(prmCinemaRoom)
        ));
        
        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    /**
     *  Metodo para pasar una instancia de tipo 'List' a 'LinkedList'
     * 
     *  @param prmList Recibe la instancia
     * 
     *  @return La lista de tipo 'LinkedList'
     */
    private LinkedListJSON listToLinkedList(List<ReserveModel> prmList)
    {
        LinkedListJSON listReserves = new LinkedListJSON();

        for(ReserveModel objReserve: prmList) listReserves.add(objReserve);
        return listReserves;
    }

    public List<ReserveModel> findSeatsWithoutReserve(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        String prmListSeats
    ) throws Exception 
    {
        LinkedListJSON listReserves = listToLinkedList(reserveDao.findAllByFunctionAndCinemaRoom(
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom
        ));
        String[] arrSeats = ChainOfCharacter.listToArray(prmListSeats);

        if(listReserves.size() < arrSeats.length) return null;

        List<ReserveModel> listMatches = new ArrayList<>();

        ReserveModel objReserveComparator = new ReserveModel(
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmEndTime, 
            prmCinemaRoom, 
            null, 
            null,
            false
        );

        for(String varSeat: arrSeats)
        {
            objReserveComparator.setRow(varSeat.charAt(0));
            objReserveComparator.setNumColumn(Integer.parseInt(ChainOfCharacter.substring(varSeat, 1, 2)));            

            if(!listReserves.contains(objReserveComparator.toJSONComplete())) return null;

            listMatches.add(new ReserveModel(
                objReserveComparator.getIdMovie(),
                objReserveComparator.getStartDate(),
                objReserveComparator.getStartTime(),
                objReserveComparator.getEndTime(),
                objReserveComparator.getCinemaRoom(),
                objReserveComparator.getRow(),
                objReserveComparator.getNumColumn(),
                true
            ));
        }
        return listMatches;
    }

    @Override
    public boolean reserveSeats(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        LocalTime prmEndTime,
        Integer prmCinemaRoom,
        String prmListSeats
    ) throws Exception 
    {
        List<ReserveModel> listReserves = findSeatsWithoutReserve(
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmEndTime,
            prmCinemaRoom, 
            prmListSeats
        );

        ServiceResponseException.throwException(
            (listReserves == null),
            "reserveSeats",
            "No hay disponibilidad de reserva para todos los asientos"
        );

        reserveDao.saveAll(listReserves);
        return true;
    }

    @Override
    public JSON delete(
        Long prmIdMovie, 
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        Integer prmCinemaRoom
    ) throws Exception {

        ReserveModel objReserveModel = reserveDao.findFirstByFunctionAndCinemaRoom(prmIdMovie, prmStartDate, prmStartTime, prmCinemaRoom);

        ServiceResponseException.throwException(
            (objReserveModel == null),
            "delete",
            "No existe la reserva para la funcion con"
            + " identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate.toString()
            + ", hora de inicio " + prmStartTime.toString()
            + " y sala " + prmCinemaRoom
        );

        reserveDao.deleteByFunctionAndCinemaRoom(prmIdMovie, prmStartDate, prmStartTime, prmCinemaRoom);

        JSON objJson = new JSON();
        objJson.add("delete", true);
        return objJson;
    }
}
