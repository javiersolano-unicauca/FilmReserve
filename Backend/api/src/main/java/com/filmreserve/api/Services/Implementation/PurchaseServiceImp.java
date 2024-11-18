package com.filmreserve.api.Services.Implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.ListSeatsValidation;
import com.filmreserve.api.Dao.iPurchaseDao;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.PurchaseModel;
import com.filmreserve.api.Models.PurchasePK;
import com.filmreserve.api.Services.iCustomerService;
import com.filmreserve.api.Services.iMovieService;
import com.filmreserve.api.Services.iPurchaseService;
import com.filmreserve.api.Services.iReserveService;

/**
 *  @author javiersolanop
 */
@Service
public class PurchaseServiceImp implements iPurchaseService {

    @Autowired
    iPurchaseDao purchaseDao;

    @Autowired
    iMovieService movieService;

    @Autowired
    iCustomerService customerService;

    @Autowired
    iReserveService reserveService;

    /**
     *  Metodo para enlistar los asientos a partir de la lista de compras
     * 
     *  @param prmList Recibe la lista de compras
     * 
     *  @return La lista de asientos si existen las compras. De lo contrario null
     */
    private LinkedListJSON purchasesToSeats(List<PurchaseModel> prmList) throws Exception
    {
        if(prmList.isEmpty()) return null;

        LinkedListJSON listSeats = new LinkedListJSON();
        JSON objSeat;
        Integer varNumColumn;

        for(PurchaseModel objPurchase: prmList)
        {
            varNumColumn = objPurchase.getCinemaRoom();
            objSeat = new JSON();
            objSeat.add(
                "seat",
                objPurchase.getRow().toString() + 
                ((varNumColumn < 10) ? ("0" + varNumColumn) : varNumColumn)
            );
            listSeats.add(objSeat);
        }
        return listSeats;
    }

    @Override
    public LinkedListJSON getPurchasedSeats(        
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmReferenceId
    ) throws Exception 
    {
        List<PurchaseModel> listPurchases = purchaseDao.find(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom, 
            prmReferenceId
        );

        ServiceResponseException.throwException(
            listPurchases.isEmpty(),
            "getPurchasedSeats",
            "No existe la compra con identificacion de cliente " + prmIdentification
            + ", identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate
            + ", hora de inicio " + prmStartTime
            + ", sala " + prmCinemaRoom
            + " y con referencia " + prmReferenceId
        );
        return purchasesToSeats(listPurchases);
    }

    @Override
    public List<PurchaseModel> getPurchases(
        Long prmIdentification, 
        Long prmIdMovie, 
        Integer prmCinemaRoom,
        LocalDate prmStartDate, 
        LocalTime prmStartTime, 
        String prmReferenceId
    ) throws Exception 
    {
        return purchaseDao.find(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom, 
            prmReferenceId
        );
    }

    @Override
    public JSON getPurchasedSeatsAndPurchase(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmReferenceId
    ) throws Exception
    {
        List<PurchaseModel> listPurchases = purchaseDao.find(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom, 
            prmReferenceId
        );

        ServiceResponseException.throwException(
            listPurchases.isEmpty(),
            "getPurchasedSeatsAndPurchase",
            "No existe la compra con identificacion de cliente " + prmIdentification
            + ", identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate
            + ", hora de inicio " + prmStartTime
            + ", sala " + prmCinemaRoom
            + " y con referencia " + prmReferenceId
        );

        PurchaseModel objPurchase = listPurchases.get(0);

        JSON objJson = new JSON(), objTotalityPurchase = new JSON();
        objTotalityPurchase.add("identification", prmIdentification);
        objTotalityPurchase.add("movie", movieService.getName(prmIdMovie));
        objTotalityPurchase.add("startDate", prmStartDate.toString());
        objTotalityPurchase.add("startTime", prmStartTime.toString());
        objTotalityPurchase.add("endTime", objPurchase.getEndTime().toString());
        objTotalityPurchase.add("cinemaRoom", prmCinemaRoom);
        objTotalityPurchase.add("value", listPurchases.size() * objPurchase.getValue());
        
        objJson.add("getPurchasedSeatsAndPurchase", true);
        objJson.add("purchase", objTotalityPurchase);
        objJson.add("purchasedSeats", getPurchasedSeats(
            prmIdentification, 
            prmIdMovie, 
            prmCinemaRoom, 
            prmStartDate, 
            prmStartTime, 
            prmReferenceId
        ));
        return objJson;
    }

    /**
     *  Metodo para pasar una lista de asientos a compras
     * 
     *  @param prmPurchase Recibe los datos de la compra
     *  @param prmPurchasedSeats Recibe la lista de asientos
     * 
     *  @return La lista con las compra
     */
    private List<PurchaseModel> seatsToPurchases(PurchaseModel prmPurchase, String prmPurchasedSeats) throws Exception
    {
        if((prmPurchase == null) || (prmPurchasedSeats.isEmpty())) return null;

        List<PurchaseModel> listPurchases = new ArrayList<>();
        String[] arrPurchasedSeats = ChainOfCharacter.listToArray(prmPurchasedSeats);
        
        for(String varPurchasedSeat: arrPurchasedSeats)
        {
            listPurchases.add(new PurchaseModel(
                prmPurchase.getIdentification(),
                prmPurchase.getIdMovie(),
                prmPurchase.getStartDate(),
                prmPurchase.getStartTime(),
                prmPurchase.getEndTime(),
                prmPurchase.getCinemaRoom(),
                varPurchasedSeat.charAt(0),
                Integer.parseInt(ChainOfCharacter.substring(varPurchasedSeat, 1, 2)),
                prmPurchase.getReferenceId(),
                prmPurchase.getPaymentId(),
                prmPurchase.getValue()
            ));
        }
        return listPurchases;
    }

    @Override
    public void validateCustomer(Long prmIdentification) throws Exception 
    {
        ServiceResponseException.throwException(
            (customerService.getUserModel(prmIdentification) == null),
            "customer",
            "No existe el cliente con identificacion " + prmIdentification
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
    public boolean save(PurchaseModel prmPurchase, String prmPurchasedSeats) throws Exception 
    {
        purchaseDao.saveAll(seatsToPurchases(prmPurchase, prmPurchasedSeats));
        reserveService.reserveSeats(
            prmPurchase.getIdMovie(),
            prmPurchase.getStartDate(),
            prmPurchase.getStartTime(),
            prmPurchase.getEndTime(),
            prmPurchase.getCinemaRoom(),
            prmPurchasedSeats
        );
        return true;
    }

    @Override
    public JSON delete(
        Long prmIdentification, 
        Long prmIdMovie, 
        Integer prmCinemaRoom, 
        LocalDate prmStartDate,
        LocalTime prmStartTime, 
        String prmReferenceId
    ) throws Exception 
    {
        ServiceResponseException.throwException(
            (!purchaseDao.exists(
                prmIdentification, 
                prmIdMovie, 
                prmStartDate, 
                prmStartTime, 
                prmCinemaRoom, 
                prmReferenceId
            )),
            "delete",
            "No existe la compra con identificacion de cliente " + prmIdentification
            + ", identificacion de pelicula " + prmIdMovie
            + ", fecha de inicio " + prmStartDate
            + ", hora de inicio " + prmStartTime
            + ", sala " + prmCinemaRoom
            + " y con referencia " + prmReferenceId
        );

        purchaseDao.deletePurchase(
            prmIdentification, 
            prmIdMovie, 
            prmStartDate, 
            prmStartTime, 
            prmCinemaRoom, 
            prmReferenceId
        );

        JSON objJson = new JSON();
        objJson.add("delete", true);
        return objJson;
    }
}
