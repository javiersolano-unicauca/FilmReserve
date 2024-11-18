package com.filmreserve.api.Services;

import java.time.LocalDate;
import java.util.List;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.MembershipModel;
import com.filmreserve.api.Models.MembershipPK;

/**
 * @author javiersolanop
 */
public interface iMembershipService {

    /**
     *  Metodo para obtener una membresia de un cliente
     *  
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmStartDate Recibe la fecha de incio de la membresia
     *  
     *  @return Una instancia JSON con la clave 'getMembership' en 'true' y con la
     *          informacion de la membresia si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getMembership(Long prmIdentification, LocalDate prmStartDate) throws Exception;

    /**
     *  Metodo para obtener una membresia de un cliente
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmStartDate Recibe la fecha de incio de la membresia
     * 
     *  @return La membresia si existe. De lo contrario null
     */
    public MembershipModel getMembershipModel(Long prmIdentification, LocalDate prmStartDate) throws Exception;

    /**
     *  Metodo para obtener el historial de membresias de un cliente
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     * 
     *  @return una instancia JSON con las membresias del cliente si existen. 
     *          De lo contrario con una clave 'getMembershipsOfCustomer' en 'false'
     *          y su respectiva causa
     *  @see JSON
     */
    public List<MembershipModel> getMembershipsOfCustomer(Long prmIdentification) throws Exception;

    /**
     *  Metodo para obtener la membresia activa de un cliente
     *  
     *  @param prmIdentification Recibe la identificacion del cliente
     *  
     *  @return Una instancia JSON con la clave 'getMembershipRecent' en 'true' y con la
     *          informacion de la membresia si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getMembershipActive(Long prmIdentification) throws Exception;

    /**
     *  Metodo para obtener la membresia activa de un cliente
     *  
     *  @param prmIdentification Recibe la identificacion del cliente
     *  
     *  @return La membresia si existe. De lo contrario null
     */
    public MembershipModel getMembershipModelActive(Long prmIdentification);

    /**
     *  Metodo para validar los datos que ingresa el usuario
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmStartDate      Recibe la fecha de inicio de la membresia
     *  @param prmEndDate        Recibe la fecha de finalizacion
     * 
     *  @throws Exception
     */
    public void validateData(Long prmIdentification, LocalDate prmStartDate, LocalDate prmEndDate) throws Exception;

    /**
     *  Metodo para guardar una membresia
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmStartDate      Recibe la fecha de inicio de la membresia
     *  @param prmEndDate        Recibe la fecha de finalizacion
     *  @param prmReferenceId    Recibe la referencia de compra
     *  @param prmPaymentId      Recibe la referecnia de pago
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede guardar la membresia
     */
    public boolean save(
        Long prmIdentification,
        LocalDate prmStartDate,
        LocalDate prmEndDate,
        String prmReferenceId,
        Long prmPaymentId
    ) throws Exception;

    /**
     *  Metodo para finalizar una membresia
     * 
     *  @param prmMembershipPK Recibe la referencia al id
     * 
     *  @return Una instancia JSON con la clave 'endMembership' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON endMembership(MembershipPK prmMembershipPK) throws Exception;

    /**
     *  Metodo para eliminar una membresia
     * 
     *  @param prmIdentification Recibe la identificacion del cliente
     *  @param prmStartDate Recibe la fecha de incio de la membresia
     * 
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON delete(Long prmIdentification, LocalDate prmStartDate) throws Exception;
}
