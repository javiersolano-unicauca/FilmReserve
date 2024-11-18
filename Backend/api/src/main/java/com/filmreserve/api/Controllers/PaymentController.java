package com.filmreserve.api.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.MembershipPK;
import com.filmreserve.api.Models.PurchasePK;
import com.filmreserve.api.Services.iMembershipPaymentService;
import com.filmreserve.api.Services.iPurchasePaymentService;

/**
 * @author javiersolanop
 */
@RestController
public class PaymentController {

    @Value("${server.address}")
    private String atrAddress;

    @Value("${server.port}")
    private String atrPort;

    @Autowired 
    iPurchasePaymentService purchasePaymentService;

    @Autowired
    iMembershipPaymentService membershipPaymentService;

    private String atrURLback = "";

    private String atrURLsuccess = "";

    @RequestMapping(path = "/api/payment/purchase-back", method = RequestMethod.GET)
    public ModelAndView savePurchseAndRedirect(
        @RequestParam(value = "preference_id") String prmPreferenceId,
        @RequestParam(value = "payment_id") Long prmPaymentId
    ) throws Exception {
        purchasePaymentService.savePurchase(prmPreferenceId, prmPaymentId);
        atrURLback = "";
        return new ModelAndView("redirect:" + atrURLsuccess);
    }

    @RequestMapping(path = "/api/payment/membership-back", method = RequestMethod.GET)
    public ModelAndView saveMembershipAndRedirect(
        @RequestParam(value = "preference_id") String prmPreferenceId,
        @RequestParam(value = "payment_id") Long prmPaymentId
    ) throws Exception {
        membershipPaymentService.saveMembership(prmPreferenceId, prmPaymentId);
        atrURLback = "";
        return new ModelAndView("redirect:" + atrURLsuccess);
    }

    private void validateURL(String prmURL) throws Exception
    {
        if((!prmURL.contains("http://")) && (!prmURL.contains("https://"))) 
            throw new Exception("El campo 'URLsuccess': Debe ser una URL valida.");
    }

    @RequestMapping(path = "/api/${api.version}/payment/purchase-payment", method = RequestMethod.POST)
    public ResponseEntity<String> generatePurchaseReference(
        Long identification,
        Long idMovie,
        Integer cinemaRoom,
        LocalDate startDate,
        LocalTime startTime,
        String listPurchasedSeats,
        String URLsuccess
    ){
        try{
            atrURLback += "http://" + atrAddress + ":" + atrPort + "/api/payment/purchase-back";
            JSON objResponse = purchasePaymentService.generatePayment(
                identification, 
                idMovie,
                cinemaRoom,
                startDate,
                startTime,
                listPurchasedSeats, 
                atrURLback
            );
            validateURL(URLsuccess);
            atrURLsuccess = URLsuccess;
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/api/${api.version}/payment/membership-payment", method = RequestMethod.POST)
    public ResponseEntity<String> generateMembershipReference(
        MembershipPK prmMembershipPK, 
        LocalDate endDate,
        String URLsuccess
    ){
        try{
            atrURLback += "http://" + atrAddress + ":" + atrPort + "/api/payment/membership-back";
            JSON objResponse = membershipPaymentService.generatePayment(prmMembershipPK, endDate, atrURLback);
            validateURL(URLsuccess);
            atrURLsuccess = URLsuccess;
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
