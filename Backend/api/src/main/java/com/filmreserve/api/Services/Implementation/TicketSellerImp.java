package com.filmreserve.api.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.TicketSellerValidation;
import com.filmreserve.api.Dao.iTicketSellerDao;
import com.filmreserve.api.Models.TicketSellerModel;
import com.filmreserve.api.Models.UserModel;
import com.filmreserve.api.Services.iTicketSellerService;

/**
 * @author javiersolanop
 */
@Service
public class TicketSellerImp extends UserServiceImp implements iTicketSellerService {

    @Autowired
    private iTicketSellerDao ticketSellerDao;

    @Override
    public UserModel getUserModel(Long prmIdentification) throws Exception 
    {
        return ticketSellerDao.findById(prmIdentification).orElse(null);
    }

    @Override
    public JSON getUser(Long prmIdentification) throws Exception
    {
        TicketSellerModel objTicketSeller = ticketSellerDao.findById(prmIdentification).orElse(null);

        ServiceResponseException.throwException(
            (objTicketSeller == null),
            "getUser",
            "No existe el usuario taquillero con identificacion " + prmIdentification
        );

        JSON objResponse = new JSON();
        objResponse.add("getUser", true);
        objResponse.add("user", objTicketSeller.toJSON());
        return objResponse;
    }

    @Override
    public JSON save(UserModel prmUser, MultipartFile prmAvatar) throws Exception
    {
        ServiceResponseException.throwException(
            !(prmUser instanceof TicketSellerModel),
            "save", 
            "La informacion debe ser de un usuario taquillero"
        );

        ServiceResponseException.throwException(
            userDao.existsById(prmUser.getIdentification()),
            "save", 
            "Ya existe ese usuario en el sistema"
        );

        try{ 

            TicketSellerValidation.validate(prmUser, prmAvatar); 
            prmUser.setAvatar(saveAvatar(prmAvatar));

        }catch(Exception e) { 
            ServiceResponseException.throwException(
                "save", 
                e.getMessage()
        ); }
        
        String varPasswordHash = BCrypt.hashpw(prmUser.getPassword(), BCrypt.gensalt());
        prmUser.setPassword(varPasswordHash);
        prmUser.setAdministrator(false);
        ticketSellerDao.save((TicketSellerModel) prmUser);

        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON delete(Long prmIdentification) throws Exception
    {
        TicketSellerModel objTicketSeller = ticketSellerDao.findById(prmIdentification).orElse(null);

        ServiceResponseException.throwException(
            (objTicketSeller == null),
            "delete",
            "No existe el usuario taquillero con identificacion " + prmIdentification
        );

        try{ removeAvatar(objTicketSeller.getAvatar()); }
        catch(Exception e){ 
            ServiceResponseException.throwException(
                "delete",
                e.getMessage()
        ); }

        ticketSellerDao.deleteById(prmIdentification);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }

    @Override
    public JSON updateTurn(Long prmIdentification, String prmTurn) throws Exception {

        TicketSellerModel objTicketSeller = ticketSellerDao.findById(prmIdentification).orElse(null);

        ServiceResponseException.throwException(
            (objTicketSeller == null),
            "update",
            "No existe el usuario taquillero con identificacion " + prmIdentification
        );

        objTicketSeller.setTurn(prmTurn);

        try{ TicketSellerValidation.validateTurn(objTicketSeller); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "update", 
                e.getMessage()
        ); }

        ticketSellerDao.save(objTicketSeller);

        JSON objJson = new JSON();
        objJson.add("update", true);
        return objJson;
    }
}
