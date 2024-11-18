package com.filmreserve.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Services.iUserService;

/**
 *  Clase para el manejo del inicio de sesion de todos los usuarios
 *  del sistema
 * 
 *  @author javiersolanop
 */
@RestController
@RequestMapping(path = "/api/${api.version}/login")
public class LoginController {

    @Autowired
    @Qualifier("UserServiceImp")
    private iUserService userService;

    /**
     *  Metodo para iniciar sesion de cualquier usuario
     * 
     *  @param identification Recibe la identificacion del usuario 
     *  @param password Recibe la contrasenia
     */
    @PostMapping
    public ResponseEntity<String> login(Long identification, String password)
    {
        try{
            JSON objJson = userService.login(identification, password);
            return new ResponseEntity<>(objJson.toString(), HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *  Metodo para actualizar la contrasenia de un usuario
     * 
     *  @param identification Recibe la identificacion del usuario       
     *  @param newPassword Recibe la nueva contrasenia
     */
    @PutMapping
    public ResponseEntity<String> updatePassword(Long identification, String newPassword)
    {
        try{
            JSON objResponse = userService.updatePassword(identification, newPassword);
            return new ResponseEntity<>(objResponse.toString(), HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } 
    }
}
