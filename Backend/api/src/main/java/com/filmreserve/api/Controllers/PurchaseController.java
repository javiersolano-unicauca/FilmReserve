package com.filmreserve.api.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Services.iPurchaseService;

/**
 *  @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/${api.version}/purchase-ticket")
public class PurchaseController {

    @Autowired
    iPurchaseService purchaseService;

    @GetMapping(path = "/{identification}/{idMovie}/{cinemaRoom}/{startDate}/{startTime}/{referenceId}")
    public ResponseEntity<String> getPurchasedSeatsAndPurchase(
        @PathVariable("identification") Long prmIdentification,
        @PathVariable("idMovie") Long prmIdMovie,
        @PathVariable("cinemaRoom") Integer prmCinemaRoom,
        @PathVariable("startDate") LocalDate prmStartDate,
        @PathVariable("startTime") LocalTime prmStartTime,
        @PathVariable("referenceId") String prmReferenceId
    ){
        try{
            JSON objResponse = purchaseService.getPurchasedSeatsAndPurchase(
                prmIdentification,
                prmIdMovie,
                prmCinemaRoom,
                prmStartDate,
                prmStartTime,
                prmReferenceId
            );
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{identification}/{idMovie}/{cinemaRoom}/{startDate}/{startTime}/{referenceId}")
    public ResponseEntity<String> delete(
        @PathVariable("identification") Long prmIdentification,
        @PathVariable("idMovie") Long prmIdMovie,
        @PathVariable("cinemaRoom") Integer prmCinemaRoom,
        @PathVariable("startDate") LocalDate prmStartDate,
        @PathVariable("startTime") LocalTime prmStartTime,
        @PathVariable("referenceId") String prmReferenceId
    ){
        try{
            JSON objResponse = purchaseService.delete(prmIdentification, prmIdMovie, prmCinemaRoom, prmStartDate, prmStartTime, prmReferenceId);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
