package com.filmreserve.api.Services.Implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.Utilities.Validations.MembershipValidation;
import com.filmreserve.api.Dao.iMembershipDao;
import com.filmreserve.api.Models.MembershipModel;
import com.filmreserve.api.Models.MembershipPK;
import com.filmreserve.api.Services.iMembershipService;

/**
 *  @author javiersolanop
 */
@Service
public class MembershipServiceImp implements iMembershipService {

    @Autowired
    private iMembershipDao membershipDao;

    @Override
    public JSON getMembership(Long prmIdentification, LocalDate prmStartDate) throws Exception
    {
        MembershipModel objMembership = membershipDao.findById(new MembershipPK(prmIdentification, prmStartDate)).orElse(null);

        ServiceResponseException.throwException(
            (objMembership == null),
            "getMembership",
            "No existe una membresia con la identificacion de cliente " + prmIdentification
            + " y fecha de inicio " + prmStartDate
        );

        JSON objJson = new JSON();
        objJson.add("getMembership", true);
        objJson.add("membership", objMembership.toJSON());
        return objJson;
    }

    @Override
    public MembershipModel getMembershipModel(Long prmIdentification, LocalDate prmStartDate) throws Exception
    {
        return membershipDao.find(prmIdentification, prmStartDate);
    }

    @Override
    public List<MembershipModel> getMembershipsOfCustomer(Long prmIdentification) throws Exception 
    {
        List<MembershipModel> listMemberships = membershipDao.findByIdentification(prmIdentification);

        ServiceResponseException.throwException(
            listMemberships.isEmpty(),
            "getMembershipsOfCustomer",
            "No existen membresias para el cliente con identificacion " + prmIdentification
        );

        return listMemberships;
    }

    @Override
    public JSON getMembershipActive(Long prmIdentification) throws Exception 
    {
        MembershipModel objMembership = membershipDao.findMembershipRecent(prmIdentification, true);

        ServiceResponseException.throwException(
            (objMembership == null),
            "getMembershipActive",
            "No existe una membresia activa con la identificacion de cliente " + prmIdentification 
        );

        JSON objJson = new JSON();
        objJson.add("getMembershipActive", true);
        objJson.add("membershipActive", objMembership.toJSON());
        return objJson;
    }

    @Override
    public MembershipModel getMembershipModelActive(Long prmIdentification) 
    {
        MembershipModel objMembershipModel = membershipDao.findMembershipRecent(prmIdentification, true);
        
        if((objMembershipModel == null)) return null;

        if(LocalDate.now().isAfter(objMembershipModel.getEndDate()))
        {
            objMembershipModel.setActive(false);
            membershipDao.save(objMembershipModel);
            return null;
        }
        return objMembershipModel;
    }

    @Override
    public void validateData(Long prmIdentification, LocalDate prmStartDate, LocalDate prmEndDate) throws Exception
    {
        try{ 
            MembershipValidation.validate(new MembershipPK(
                prmIdentification, 
                prmStartDate),
                prmEndDate
            ); 
        } catch(Exception e) { 
            ServiceResponseException.throwException(
                "data", 
                e.getMessage()
        ); }
    }

    @Override
    public boolean save(
        Long prmIdentification,
        LocalDate prmStartDate,
        LocalDate prmEndDate,
        String prmReferenceId,
        Long prmPaymentId
    ) throws Exception 
    {
        membershipDao.save(new MembershipModel(
            prmIdentification, 
            prmStartDate, 
            prmEndDate, 
            true, 
            prmReferenceId, 
            prmPaymentId
        ));
        return true;
    }

    @Override
    public JSON endMembership(MembershipPK prmMembershipPK) throws Exception 
    {
        MembershipModel objMembership = membershipDao.findById(prmMembershipPK).orElse(null);

        ServiceResponseException.throwException(
            (objMembership == null),
            "endMembership",
            "No existe una membresia con la identificacion de cliente " + prmMembershipPK.getIdentification()
            + " y fecha de inicio " + prmMembershipPK.getStartDate()
        );

        objMembership.setActive(false);
        membershipDao.save(objMembership);

        JSON objJson = new JSON();
        objJson.add("endMembership", true);
        return objJson;
    }

    @Override
    public JSON delete(Long prmIdentification, LocalDate prmStartDate) throws Exception 
    {
        MembershipPK objMembershipPK = new MembershipPK(prmIdentification, prmStartDate);

        ServiceResponseException.throwException(
            !membershipDao.existsById(objMembershipPK),
            "delete",
            "No existe una membresia con la identificacion de cliente " + prmIdentification
            + " y fecha de inicio " + prmStartDate
        );

        membershipDao.deleteById(objMembershipPK);

        JSON objJson = new JSON();
        objJson.add("delete", true);
        return objJson;
    }
}
