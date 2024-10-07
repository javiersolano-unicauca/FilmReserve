package com.filmreserve.api.Services.Implementation;

import org.springframework.stereotype.Service;

import com.filmreserve.Libraries.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Exceptions.ServiceResponseException;
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
    public JSON save(UserModel prmUser) throws Exception
    {
        prmUser.setAdministrator(true);
        return super.save(prmUser);
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

        userDao.deleteById(prmIdentification);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }
}
