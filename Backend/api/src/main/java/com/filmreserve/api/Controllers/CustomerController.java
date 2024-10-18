package com.filmreserve.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.CustomerModel;
import com.filmreserve.api.Services.iCustomerService;

/**
 * Clase controladora para la gestion de clientes del sistema
 * 
 * @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/v2/customer")
public class CustomerController{

    @Autowired
    private iCustomerService customerService;
    
    /**
     *  Metodo para obtener la informacion de un usuario por su identificacion
     * 
     *  @param prmId Recibe la identificacion del usuario
     */
    @GetMapping(path = "/{identification}")
    public ResponseEntity<String> get(@PathVariable("identification") Long prmId)
    {
        try{
            JSON objResponse = customerService.getUser(prmId);
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
     *  @param prmCustomer Recibe la informacion del cliente
     *  @param avatarImage Recibe la imagen del avatar
     */
    @PostMapping(path = "/save")
    public ResponseEntity<String> save(CustomerModel prmCustomer, MultipartFile avatarImage)
    {
        try{
            JSON objResponse = customerService.save(prmCustomer, avatarImage);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *  Metodo para eliminar un usuario
     * 
     *  @param prmId Recibe la identificacion del usuario 
     */
    @DeleteMapping(path = "/{identification}")
    public ResponseEntity<String> delete(@PathVariable("identification") Long prmId)
    {
        try{
            JSON objResponse = customerService.delete(prmId);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    } 
}
