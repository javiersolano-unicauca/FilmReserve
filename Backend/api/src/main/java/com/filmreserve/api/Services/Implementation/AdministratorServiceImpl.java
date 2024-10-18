package com.filmreserve.api.Services.Implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.api.Models.UserModel;
import com.filmreserve.api.Services.iAdministratorService;

@Service
public class AdministratorServiceImpl extends UserServiceImp implements iAdministratorService {

    @Override
    public JSON getUser(Long prmIdentification) throws Exception 
    {
        UserModel objUser = getUserModel(prmIdentification);

        ServiceResponseException.throwException(
            ((objUser == null) || !objUser.getAdministrator()),
            "getUser",
            "No existe el usuario administrador con identificacion " + prmIdentification
        );
        
        JSON objResponse = new JSON();
        objResponse.add("getUser", true);
        objResponse.add("user", objUser.toJSON());
        return objResponse;
    }

    @Override
    public JSON save(UserModel prmUser, MultipartFile prmAvatar) throws Exception
    {
        prmUser.setAdministrator(true);
        return super.save(prmUser, prmAvatar);
    }

    @Override
    public JSON delete(Long prmIdentification) throws Exception
    {
        UserModel objUser = getUserModel(prmIdentification);

        ServiceResponseException.throwException(
            ((objUser == null) || !objUser.getAdministrator()),
            "delete",
            "No existe el usuario administrador con identificacion " + prmIdentification
        );

        try{ removeAvatar(objUser.getAvatar()); }
        catch(Exception e){ 
            ServiceResponseException.throwException(
                "delete",
                e.getMessage()
        ); }

        userDao.deleteById(prmIdentification);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }
}
