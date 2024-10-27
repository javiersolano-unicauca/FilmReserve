package com.filmreserve.api.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.LinkedList;

@RestController
@RequestMapping(path = "/api/v2/test")
public class TestController {


    @GetMapping
    public ResponseEntity<Object> save() throws Exception
    {
        try
        {
            LinkedList<JSON> listJSON = new LinkedList<>();
            return new ResponseEntity<>((listJSON instanceof LinkedList<JSON>) ? "true" : "false", HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
