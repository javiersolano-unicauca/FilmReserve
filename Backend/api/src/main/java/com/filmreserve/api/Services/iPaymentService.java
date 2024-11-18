package com.filmreserve.api.Services;

/**
 *  @author javiersolanop
 */
public interface iPaymentService {

    /**
     *  Metodo para crear una referencia de compra
     * 
     *  @param prmDescription Recibe la descripcion de la compra
     *  @param prmAmount Recibe la cantidad de productos
     *  @param prmUnitValue Recibe el valor unitario de cada producto
     *  @param prmURLback Recibe la URL para retorno
     *  
     *  @return La referencia de compra
     */
    public String generatePurchaseReference(
        String prmDescription,
        Integer prmAmount, 
        Long prmUnitValue, 
        String prmURLback
    ) throws Exception;
}
