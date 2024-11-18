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
import com.filmreserve.api.Models.UserModel;
import com.filmreserve.api.Services.iAdministratorService;

/**
 * Clase controladora para la gestion de administradores del sistema
 * 
 * @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/${api.version}/administrator")
public class AdministratorController {

    @Autowired
    private iAdministratorService administratorService;

    /**
     *  Metodo para obtener la informacion de un usuario por su identificacion
     * 
     *  @param prmId Recibe la identificacion del usuario
     */
    @GetMapping(path = "/{identification}")
    public ResponseEntity<String> get(@PathVariable("identification") Long prmId)
    {
        try{
            JSON objResponse = administratorService.getUser(prmId);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Metodo para guardar un usuario en el sistema
     * 
     *  @param prmUser Recibe la informacion del usuario
     *  @param avatarImage Recibe la imagen del avatar 
     */
    @PostMapping(path = "/save")
    public ResponseEntity<String> save(UserModel prmUser, MultipartFile avatarImage)
    {
        try{
            JSON objResponse = administratorService.save(prmUser, avatarImage);
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
            JSON objResponse = administratorService.delete(prmId);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    } 
}
