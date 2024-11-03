package com.filmreserve.api.Controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.MembershipModel;
import com.filmreserve.api.Models.MembershipPK;
import com.filmreserve.api.Services.iMembershipService;

/**
 * Clase controladora para la gestion de membresias
 * 
 * @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/v3/membership")
public class MembershipController {

    @Autowired
    private iMembershipService membershipService;

    @GetMapping(path = "/{identification}/{startDate}")
    public ResponseEntity<String> getMembership(
        @PathVariable("identification") Long prmIdentification,
        @PathVariable("startDate") LocalDate prmStartDate
    ){
        try{
            JSON objResponse = membershipService.getMembership(prmIdentification, prmStartDate);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/customer/{identification}")
    public ResponseEntity<Object> getMembershipsOfCustomer(@PathVariable("identification") Long prmIdentification)
    {
        try{
            return new ResponseEntity<>(membershipService.getMembershipsOfCustomer(prmIdentification), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/active/{identification}")
    public ResponseEntity<String> getMembershipActive(@PathVariable("identification") Long prmIdentification)
    {
        try{
            JSON objResponse = membershipService.getMembershipActive(prmIdentification);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> save(MembershipPK prmMembershipPK, MembershipModel prmMembership)
    {
        try{
            JSON objResponse = membershipService.save(prmMembershipPK, prmMembership);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/end")
    public ResponseEntity<String> endMembership(MembershipPK prmMembershipPK)
    {
        try{
            JSON objResponse = membershipService.endMembership(prmMembershipPK);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{identification}/{startDate}")
    public ResponseEntity<String> delete(
        @PathVariable("identification") Long prmIdentification,
        @PathVariable("startDate") LocalDate prmStartDate
    ){
        try{
            JSON objResponse = membershipService.delete(prmIdentification, prmStartDate);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
