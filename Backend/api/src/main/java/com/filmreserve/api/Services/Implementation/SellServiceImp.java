package com.filmreserve.api.Services.Implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.ListSeatsValidation;
import com.filmreserve.api.Dao.iSellDao;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.SellModel;
import com.filmreserve.api.Services.iMovieService;
import com.filmreserve.api.Services.iReserveService;
import com.filmreserve.api.Services.iSellService;
import com.filmreserve.api.Services.iTicketSellerService;

/**
 *  @author javiersolanop
 */
@Service
public class SellServiceImp implements iSellService {

    @Value("${ticket_value}")
    private Long atrValue;

    @Autowired
    iSellDao sellDao;

    @Autowired
    iMovieService movieService;

    @Autowired
    iTicketSellerService ticketSellerService;

    @Autowired
    iReserveService reserveService;

    /**
     *  Metodo para enlistar los asientos a partir de la lista de compras
     * 
     *  @param prmList Recibe la lista de ventas
     * 
     *  @return La lista de asientos si existen las ventas. De lo contrario null
     */
    private LinkedListJSON salesToSeats(List<SellModel> prmList) throws Exception
    {
        if(prmList.isEmpty()) return null;

        LinkedListJSON listSeats = new LinkedListJSON();
        JSON objSeat;
        Integer varNumColumn;

        for(SellModel objSell: prmList)
        {
            varNumColumn = objSell.getCinemaRoom();
            objSeat = new JSON();
            objSeat.add(
                "seat",
                objSell.getRow().toString() + 
                ((varNumColumn < 10) ? ("0" + varNumColumn) : varNumColumn)
            );
            listSeats.add(objSeat);
        }
        return listSeats;
    }

    @Override
    public LinkedListJSON getSelledSeats(        
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception 
    {
        List<SellModel> listSales = sellDao.find(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom
        );

        ServiceResponseException.throwException(
            listSales.isEmpty(),
            "getSelldSeats",
            "No existe la venta con identificacion de taquillero " + prmIdentification
            + ", identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate
            + ", hora de inicio " + prmStartTime
            + " y sala " + prmCinemaRoom
        );
        return salesToSeats(listSales);
    }

    @Override
    public List<SellModel> getSales(
        Long prmIdentification, 
        Long prmIdMovie, 
        Integer prmCinemaRoom,
        LocalDate prmStartDate, 
        LocalTime prmStartTime
    ) throws Exception 
    {
        return sellDao.find(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom
        );
    }

    @Override
    public JSON getSelledSeatsAndSell(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception
    {
        List<SellModel> listSales = sellDao.find(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom
        );

        ServiceResponseException.throwException(
            listSales.isEmpty(),
            "getSelledSeatsAndSell",
            "No existe la venta con identificacion de taquillero " + prmIdentification
            + ", identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate
            + ", hora de inicio " + prmStartTime
            + " y sala " + prmCinemaRoom
        );

        SellModel objSell = listSales.get(0);

        JSON objJson = new JSON(), objTotalityPurchase = new JSON();
        objTotalityPurchase.add("identification", prmIdentification);
        objTotalityPurchase.add("movie", movieService.getName(prmIdMovie));
        objTotalityPurchase.add("startDate", prmStartDate.toString());
        objTotalityPurchase.add("startTime", prmStartTime.toString());
        objTotalityPurchase.add("endTime", objSell.getEndTime().toString());
        objTotalityPurchase.add("cinemaRoom", prmCinemaRoom);
        objTotalityPurchase.add("value", listSales.size() * objSell.getValue());
        
        objJson.add("getSelledSeatsAndSell", true);
        objJson.add("sell", objTotalityPurchase);
        objJson.add("selledSeats", getSelledSeats(
            prmIdentification, 
            prmIdMovie, 
            prmCinemaRoom, 
            prmStartDate, 
            prmStartTime
        ));
        return objJson;
    }

    /**
     *  Metodo para pasar una lista de asientos a vender
     * 
     *  @param prmPurchase Recibe los datos de la venta
     *  @param prmPurchasedSeats Recibe la lista de asientos
     * 
     *  @return La lista con las ventas
     */
    private List<SellModel> seatsToSales(SellModel prmSell, String prmSelledSeats) throws Exception
    {
        if((prmSell == null) || (prmSelledSeats.isEmpty())) return null;

        List<SellModel> listSales = new ArrayList<>();
        String[] arrSelledSeats = ChainOfCharacter.listToArray(prmSelledSeats);
        
        for(String varSelledSeat: arrSelledSeats)
        {
            listSales.add(new SellModel(
                prmSell.getIdentification(),
                prmSell.getIdMovie(),
                prmSell.getStartDate(),
                prmSell.getStartTime(),
                prmSell.getEndTime(),
                prmSell.getCinemaRoom(),
                varSelledSeat.charAt(0),
                Integer.parseInt(ChainOfCharacter.substring(varSelledSeat, 1, 2)),
                prmSell.getValue()
            ));
        }
        return listSales;
    }

    @Override
    public void validateTicketSeller(Long prmIdentification) throws Exception 
    {
        ServiceResponseException.throwException(
            (ticketSellerService.getUserModel(prmIdentification) == null),
            "ticketSeller",
            "No existe el taquillero con identificacion " + prmIdentification
        );
    }

    @Override
    public FunctionModel getFunctionModel(
        Long prmIdMovie, 
        Integer prmCinemaRoom, 
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception 
    {
        FunctionModel objFunction = reserveService.getFunctionModel(
            prmIdMovie,
            prmStartDate,
            prmStartTime,
            prmCinemaRoom
        );

        ServiceResponseException.throwException(
            (objFunction == null),
            "function",
            "No existe la reserva para la funcion con"
            + " identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate.toString()
            + ", hora de inicio " + prmStartTime.toString()
            + " y sala " + prmCinemaRoom
        );
        return objFunction;
    }

    @Override
    public boolean validateSeatAvailability(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        LocalTime prmEndTime,
        String prmSeats
    ) throws Exception 
    {
        try{ ListSeatsValidation.validate(prmSeats); }
        catch(Exception e){
            ServiceResponseException.throwException(
                "seats", 
                e.getMessage()
        );}

        ServiceResponseException.throwException(
            (reserveService.findSeatsWithoutReserve(
                prmIdMovie, 
                prmStartDate, 
                prmStartTime, 
                prmEndTime,
                prmCinemaRoom, 
                prmSeats
            ) == null),
            "seats",
            "Alguno o todos los asientos estan reservados"
        );
        return true;
    }

    @Override
    public JSON save(
        Long prmIdentification, 
        Long prmIdMovie, 
        Integer prmCinemaRoom, 
        LocalDate prmStartDate,
        LocalTime prmStartTime, 
        String prmSelledSeats
    ) throws Exception 
    {
        validateTicketSeller(prmIdentification);

        FunctionModel objFunction = getFunctionModel(
            prmIdMovie,
            prmCinemaRoom,
            prmStartDate,
            prmStartTime
        );

        validateSeatAvailability(
            prmIdentification,
            prmIdMovie,
            prmCinemaRoom,
            prmStartDate,
            prmStartTime,
            objFunction.getEndTime(),
            prmSelledSeats
        );

        sellDao.saveAll(seatsToSales(new SellModel(
            prmIdentification,
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            objFunction.getEndTime(), 
            prmCinemaRoom, 
            null, 
            null, 
            atrValue
        ), prmSelledSeats));

        reserveService.reserveSeats(
            prmIdMovie,
            prmStartDate,
            prmStartTime,
            objFunction.getEndTime(),
            prmCinemaRoom,
            prmSelledSeats
        );

        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON delete(
        Long prmIdentification, 
        Long prmIdMovie, 
        Integer prmCinemaRoom, 
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception 
    {
        ServiceResponseException.throwException(
            (!sellDao.exists(
                prmIdentification, 
                prmIdMovie, 
                prmStartDate, 
                prmStartTime, 
                prmCinemaRoom
            )),
            "delete",
            "No existe la venta con identificacion de taquillero " + prmIdentification
            + ", identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate
            + ", hora de inicio " + prmStartTime
            + " y sala " + prmCinemaRoom
        );

        sellDao.deleteSell(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom
        );

        JSON objJson = new JSON();
        objJson.add("delete", true);
        return objJson;
    }
}
