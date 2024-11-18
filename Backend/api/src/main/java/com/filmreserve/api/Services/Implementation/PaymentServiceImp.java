package com.filmreserve.api.Services.Implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.filmreserve.Utilities.ModelsException.ServiceResponseException;
import com.filmreserve.api.Services.iPaymentService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;

/**
 *  @author javiersolanop
 */
@Service
public class PaymentServiceImp implements iPaymentService {

    @Value("${access_token}")
    private String atrAccessToken;

    @Override
    public String generatePurchaseReference(
        String prmDescription,
        Integer prmAmount, 
        Long prmUnitValue, 
        String prmURLback
    ) throws Exception
    {
        try{
            MercadoPagoConfig.setAccessToken(atrAccessToken);
        
            PreferenceItemRequest objItemRequest = PreferenceItemRequest
            .builder()
            .title(prmDescription)
            .quantity(prmAmount)
            .unitPrice(new BigDecimal(prmUnitValue))
            .currencyId("COL")
            .build();

            List<PreferenceItemRequest> listItemRequests = new ArrayList<>();
            listItemRequests.add(objItemRequest);

            PreferenceBackUrlsRequest objPreferenceBackUrls = PreferenceBackUrlsRequest
            .builder()
            .success(prmURLback)
            .build();

            PreferenceRequest objPreferenceRequest = PreferenceRequest
            .builder()
            .items(listItemRequests)
            .backUrls(objPreferenceBackUrls)
            .build();
            
            return new PreferenceClient().create(objPreferenceRequest).getId();
        }
        catch(Exception e)
        {
            ServiceResponseException.throwException("generatePurchaseReference", e.getMessage());
        }
        return null;
    }
}
