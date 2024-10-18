package com.filmreserve.api.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;

@RestController
@RequestMapping(path = "/api/v2/test")
public class TestController {

    @PostMapping
    public ResponseEntity<String[]> save(String listGenders) throws Exception
    {
        return new ResponseEntity<>(ChainOfCharacter.toArray(listGenders), HttpStatus.OK);
    }
}
