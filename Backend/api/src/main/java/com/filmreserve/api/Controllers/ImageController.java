package com.filmreserve.api.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.filmreserve.Utilities.Files.File;

@RestController
@RequestMapping(path = "/api/")
public class ImageController {

    @GetMapping(
        path = "/posters/{posterImage}",
        produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getPoster(@PathVariable("posterImage") String prmPoster) throws Exception
    {
        return new File("posters").importJpeg(prmPoster);
    }

    @GetMapping(
        path = "/avatars/{avatar}",
        produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getAvatar(@PathVariable("avatar") String prmAvatar) throws Exception
    {
        return new File("avatars").importJpeg(prmAvatar);
    }
}
