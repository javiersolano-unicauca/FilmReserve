package com.filmreserve.api.Services.Implementation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.Lists.SuperLinkedList;
import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.api.Models.MembershipModel;
import com.filmreserve.api.Models.MembershipPK;
import com.filmreserve.api.Services.iCustomerService;
import com.filmreserve.api.Services.iMembershipPaymentService;
import com.filmreserve.api.Services.iMembershipService;

/**
 *  @author javiersolanop
 */
@Service
public class MembershipPaymentServiceImp extends PaymentServiceImp implements iMembershipPaymentService {

    @Value("${membership_value}")
    private Long atrValue;

    @Autowired
    private iCustomerService customerService;

    @Autowired
    private iMembershipService membershipService;

    private SuperLinkedList<String, MembershipModel> listMemberships = new SuperLinkedList<>();

    @Override
    public JSON generatePayment(
        MembershipPK prmMembershipPK, 
        LocalDate prmEndDate, 
        String prmURLback
    ) throws Exception 
    {
        ServiceResponseException.throwException(
            (customerService.getUserModel(prmMembershipPK.getIdentification()) == null),
            "generatePayment",
            "No existe el cliente con identificacion " + prmMembershipPK.getIdentification()
        );

        MembershipModel objMembership = membershipService.getMembershipModelActive(prmMembershipPK.getIdentification());

        ServiceResponseException.throwException(
            (objMembership != null),
            "generatePayment",
            "Ya se encuentra una membresia activa para el cliente con identificacion " + prmMembershipPK.getIdentification()
        );

        objMembership = membershipService.getMembershipModel(
            prmMembershipPK.getIdentification(), 
            prmMembershipPK.getStartDate()
        );

        ServiceResponseException.throwException(
            (objMembership != null),
            "save",
            "Ya se encuentra una membresia con la identificacion de cliente " + prmMembershipPK.getIdentification()
            + " entre las fechas " + ((objMembership != null) ? objMembership.getStartDate().toString() : "") 
            + " y " + ((objMembership != null) ? objMembership.getEndDate().toString() : "")    
        );

        membershipService.validateData(
            prmMembershipPK.getIdentification(),
            prmMembershipPK.getStartDate(),
            prmEndDate
        );

        try{
            String varReferenceId = generatePurchaseReference(
                "Activacion de membresia", 
                1, 
                atrValue, 
                prmURLback
            );

            objMembership = new MembershipModel();
            objMembership.setIdentification(prmMembershipPK.getIdentification());
            objMembership.setStartDate(prmMembershipPK.getStartDate());
            objMembership.setEndDate(prmEndDate);
            objMembership.setReferenceId(varReferenceId);

            listMemberships.add(varReferenceId, objMembership);

            JSON objResponse = new JSON();
            objResponse.add("generatePayment", true);
            objResponse.add("referenceId", varReferenceId);
            return objResponse;

        }catch(Exception e) { 
            ServiceResponseException.throwException(
                "generatePayment", 
                e.getMessage()
        ); }
        return null;
    }

    @Override
    public boolean saveMembership(String prmReferenceId, Long paymentId) throws Exception
    {
        MembershipModel objMembership = listMemberships.get(prmReferenceId);
        objMembership.setPaymentId(paymentId);

        listMemberships.remove(prmReferenceId);

        return membershipService.save(
            objMembership.getIdentification(),
            objMembership.getStartDate(),
            objMembership.getEndDate(),
            objMembership.getReferenceId(),
            objMembership.getPaymentId()
        );
    }
}
