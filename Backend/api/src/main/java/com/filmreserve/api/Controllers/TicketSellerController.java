package com.filmreserve.api.Controllers;

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
import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.TicketSellerModel;
import com.filmreserve.api.Services.iTicketSellerService;

/**
 *  Clase controladora para la gestion de taquilleros
 * 
 *  @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/v3/ticket-seller")
public class TicketSellerController {

    @Autowired
    private iTicketSellerService ticketSellerService;

    /**
     *  Metodo para obtener la informacion de un taquillero por su identificacion
     * 
     *  @param prmId Recibe la identificacion del taquillero
     */
    @GetMapping(path = "/{identification}")
    public ResponseEntity<String> get(@PathVariable("identification") Long prmId)
    {
        try{
            JSON objResponse = ticketSellerService.getUser(prmId);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Metodo para guardar un taquillero en el sistema
     * 
     *  @param prmTicketSeller Recibe la informacion del taquillero
     *  @param avatarImage Recibe la imagen del avatar 
     */
    @PostMapping(path = "/save")
    public ResponseEntity<String> save(TicketSellerModel prmTicketSeller, MultipartFile avatarImage)
    {
        try{
            JSON objResponse = ticketSellerService.save(prmTicketSeller, avatarImage);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

     /**
     *  Metodo para eliminar un taquillero
     * 
     *  @param prmId Recibe la identificacion del taquillero 
     */
    @DeleteMapping(path = "/{identification}")
    public ResponseEntity<String> delete(@PathVariable("identification") Long prmId)
    {
        try{
            JSON objResponse = ticketSellerService.delete(prmId);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    } 

    /**
     *  Metodo para actualizar el turno de un taquillero
     * 
     *  @param identification Recibe la identificacion del taquillero       
     *  @param turn Recibe el nuevo turno
     */
    @PutMapping
    public ResponseEntity<String> updateTurn(Long identification, String turn)
    {
        try{
            JSON objResponse = ticketSellerService.updateTurn(identification, turn);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } 
    }
}
