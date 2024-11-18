package com.filmreserve.api.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedList;
import com.filmreserve.api.Dao.iMovieDao;
import com.filmreserve.api.Dao.iPurchaseDao;
import com.filmreserve.api.Models.PurchaseModel;
import com.filmreserve.api.Models.PurchasePK;
import com.filmreserve.api.Services.iPurchaseService;

@RestController
@RequestMapping(path = "/api/${api.version}/test")
public class TestController {

    @Autowired
    iPurchaseService purchaseService;

    @GetMapping
    public Object test()
    {
        try{
            // return purchaseService.getPurchasedSeatsAndPurchase(new PurchasePK(
            //     Long.parseLong("1234567890"),
            //     Long.parseLong("1"),
            //     Integer.parseInt("1"),
            //     LocalDate.of(2025, 11,11),
            //     LocalTime.of(17, 48),
            //     "1b2v34"
            // )).toString();
            return Integer.parseInt("01");
        }catch(Exception e){ return e.getMessage(); }
    }
}
