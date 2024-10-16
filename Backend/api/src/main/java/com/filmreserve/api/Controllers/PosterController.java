package com.filmreserve.api.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Files.File;

@RestController
@RequestMapping(path = "/posters")
public class PosterController {

    @GetMapping(
        path = "/{posterImage}",
        produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] get(@PathVariable("posterImage") String prmPoster) throws Exception
    {
        return new File("posters").importJpeg(prmPoster);
    }
}
