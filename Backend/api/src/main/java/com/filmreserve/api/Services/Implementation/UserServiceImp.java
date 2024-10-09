package com.filmreserve.api.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.UserValidation;
import com.filmreserve.api.Dao.iUserDao;
import com.filmreserve.api.Models.UserModel;
import com.filmreserve.api.Services.iUserService;

@Service("UserServiceImp")
public class UserServiceImp implements iUserService {

    @Autowired
    protected iUserDao userDao;

    @Override
    public JSON login(Long prmIdentification, String prmPassword) throws Exception
    {
        UserModel objUser = userDao.findById(prmIdentification).orElse(null);

        ServiceResponseException.throwException(
            (objUser == null),
            "login",
            "No existe el usuario con identificacion " + prmIdentification
        );

        ServiceResponseException.throwException(
            !BCrypt.checkpw(prmPassword, objUser.getPassword()),
            "login",
            "Contraseña incorrecta"
        );

        JSON objResponse = new JSON();
        objResponse.add("login", true);
        objResponse.add("user", objUser.toJSON());
        return objResponse;
    }

    @Override
    public UserModel getUserModel(Long prmIdentification) throws Exception
    {
        return userDao.findById(prmIdentification).orElse(null);
    }

    @Override
    public JSON getUser(Long prmIdentification) throws Exception
    {
        UserModel objUser = getUserModel(prmIdentification);

        ServiceResponseException.throwException(
            (objUser == null),
            "getUser",
            "No existe el usuario con identificacion " + prmIdentification
        );

        JSON objResponse = new JSON();
        objResponse.add("getUser", true);
        objResponse.add("user", objUser.toJSON());
        return objResponse;
    }

    @Override
    public JSON save(UserModel prmUser) throws Exception
    {
        ServiceResponseException.throwException(
            userDao.existsById(prmUser.getIdentification()),
            "save",
            "Ya existe ese usuario en el sistema"
        );

        try{ UserValidation.validate(prmUser); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "save", 
                e.getMessage()
        ); }

        String varPasswordHash = BCrypt.hashpw(prmUser.getPassword(), BCrypt.gensalt());
        prmUser.setPassword(varPasswordHash);
        userDao.save(prmUser);

        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON updatePassword(Long prmIdentification, String prmPassword) throws Exception
    {
        UserModel objUser = userDao.findById(prmIdentification).orElse(null);

        ServiceResponseException.throwException(
            (objUser == null),
            "update",
            "No existe el usuario con identificacion " + prmIdentification
        );

        String varPasswordHash = BCrypt.hashpw(prmPassword, BCrypt.gensalt());
        objUser.setPassword(varPasswordHash);

        try{ UserValidation.validatePassword(objUser); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "update", 
                e.getMessage()
        ); }

        userDao.save(objUser);

        JSON objJson = new JSON();
        objJson.add("update", true);
        return objJson;
    }

    @Override
    public JSON delete(Long prmIdentification) throws Exception
    {
        ServiceResponseException.throwException(
            !userDao.existsById(prmIdentification),
            "delete",
            "No existe el usuario con identificacion " + prmIdentification
        );

        userDao.deleteById(prmIdentification);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }
}
 