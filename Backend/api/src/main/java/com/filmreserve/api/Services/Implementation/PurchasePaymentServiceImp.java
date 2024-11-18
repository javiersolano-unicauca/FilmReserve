package com.filmreserve.api.Services.Implementation;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.SuperLinkedList;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.PurchaseModel;
import com.filmreserve.api.Services.iMembershipService;
import com.filmreserve.api.Services.iPurchasePaymentService;
import com.filmreserve.api.Services.iPurchaseService;

/**
 *  @author javiersolanop
 */
@Service
public class PurchasePaymentServiceImp extends PaymentServiceImp implements iPurchasePaymentService {

    @Value("${discount}")
    private Integer atrDiscount;

    @Value("${ticket_value}")
    private Long atrTicketValue;

    @Autowired
    private iMembershipService membershipService;

    @Autowired
    private iPurchaseService purchaseService;

    private SuperLinkedList<String, PurchaseModel> listPurchases = new SuperLinkedList<>();
 
    private SuperLinkedList<String, String> listPurchasedSeats = new SuperLinkedList<>();

    /**
     *  Metodo para establecer el valor de la compra
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     */
    private Long setUnitValue(Long prmIdentification)
    {
        Long varValue = atrTicketValue;

        if(membershipService.getMembershipModelActive(prmIdentification) != null)
            varValue = (long) ((varValue * (100 - atrDiscount))/100);
        return varValue;
    }

    @Override
    public JSON generatePayment(
        Long prmIdentification,
        Long prmIdMovie,
        Integer prmCinemaRoom,
        LocalDate prmStartDate,
        LocalTime prmStartTime,
        String prmPurchasedSeats,
        String prmURLback
    ) throws Exception
    {
        purchaseService.validateCustomer(prmIdentification);

        FunctionModel objFunction = purchaseService.getFunctionModel(
            prmIdMovie,
            prmCinemaRoom,
            prmStartDate,
            prmStartTime
        );

        purchaseService.validateSeatAvailability(
            prmIdentification,
            prmIdMovie,
            prmCinemaRoom,
            prmStartDate,
            prmStartTime,
            objFunction.getEndTime(),
            prmPurchasedSeats
        );
        
        try{ 
            PurchaseModel objPurchase = new PurchaseModel();
            int varAmountSeats = ChainOfCharacter.amountRepeats(prmPurchasedSeats, ',') + 1;

            Long varUnitValue = setUnitValue(prmIdentification);
            objPurchase.setValue(varUnitValue);
            String varReferenceId = generatePurchaseReference(
                "Tiquetes de CineCol",
                varAmountSeats, 
                varUnitValue, 
                prmURLback
            );

            objPurchase.setIdentification(prmIdentification);
            objPurchase.setIdMovie(prmIdMovie);
            objPurchase.setStartDate(prmStartDate);
            objPurchase.setStartTime(prmStartTime);
            objPurchase.setEndTime(objFunction.getEndTime());
            objPurchase.setCinemaRoom(prmCinemaRoom);
            objPurchase.setReferenceId(varReferenceId);

            listPurchases.add(varReferenceId, objPurchase);
            listPurchasedSeats.add(varReferenceId, prmPurchasedSeats);

            JSON objResponse = new JSON();
            objResponse.add("generatePayment", true);
            objResponse.add("referenceId", varReferenceId);
            return objResponse;

        }catch(Exception e) { 
            ServiceResponseException.throwException(
                "generatePayment", 
                e.getMessage()
        ); }
        return null;
    }

    @Override
    public boolean savePurchase(String prmReferenceId, Long paymentId) throws Exception
    {
        PurchaseModel objPurchase = listPurchases.get(prmReferenceId);
        objPurchase.setPaymentId(paymentId);

        String varPurchasedSeats = listPurchasedSeats.get(prmReferenceId);

        listPurchases.remove(prmReferenceId);
        listPurchasedSeats.remove(prmReferenceId);

        return purchaseService.save(objPurchase, varPurchasedSeats);
    }
}
