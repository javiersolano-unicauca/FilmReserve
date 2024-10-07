package com.filmreserve.api.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.filmreserve.Libraries.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Exceptions.ServiceResponseException;
import com.filmreserve.Utilities.Validations.CustomerValidation;
import com.filmreserve.api.Dao.iCustomerDao;
import com.filmreserve.api.Models.CustomerModel;
import com.filmreserve.api.Models.UserModel;
import com.filmreserve.api.Services.iCustomerService;

/**
 * @author javiersolanop
 */
@Service
public class CustomerServiceImp extends UserServiceImp implements iCustomerService {

    @Autowired
    private iCustomerDao customerDao;

    @Override
    public JSON getUser(Long prmIdentification) throws Exception
    {
        CustomerModel objCustomer = customerDao.findById(prmIdentification).orElse(null);

        ServiceResponseException.throwException(
            (objCustomer == null),
            "getUser",
            "No existe el usuario cliente con identificacion " + prmIdentification
        );

        JSON objResponse = new JSON();
        objResponse.add("getUser", true);
        objResponse.add("user", objCustomer.toJSON());
        return objResponse;
    }

    @Override
    public JSON save(UserModel prmUser) throws Exception
    {
        ServiceResponseException.throwException(
            !(prmUser instanceof CustomerModel),
            "save", 
            "La informacion debe ser de un usuario cliente"
        );

        ServiceResponseException.throwException(
            customerDao.existsById(prmUser.getIdentification()),
            "save", 
            "Ya existe ese usuario en el sistema"
        );

        try{ CustomerValidation.validate(prmUser); }
        catch(Exception e) { 
            ServiceResponseException.throwException(
                "save", 
                e.getMessage()
        ); }
        
        String varPasswordHash = BCrypt.hashpw(prmUser.getPassword(), BCrypt.gensalt());
        prmUser.setPassword(varPasswordHash);
        prmUser.setAdministrator(false);
        customerDao.save((CustomerModel) prmUser);

        JSON objJson = new JSON();
        objJson.add("save", true);
        return objJson;
    }

    @Override
    public JSON delete(Long prmIdentification) throws Exception
    {
        ServiceResponseException.throwException(
            !userDao.existsById(prmIdentification),
            "delete",
            "No existe el usuario cliente con identificacion " + prmIdentification
        );

        customerDao.deleteById(prmIdentification);

        JSON objResponse = new JSON();
        objResponse.add("delete", true);
        return objResponse;
    }
}
