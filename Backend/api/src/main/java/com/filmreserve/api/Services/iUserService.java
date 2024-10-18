package com.filmreserve.api.Services;

import org.springframework.web.multipart.MultipartFile;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.UserModel;

/** 
 * @author javiersolanop
 */
public interface iUserService {

    /**
     *  Metodo para el inicio de sesion de un usuario
     * 
     *  @param prmIdentification Recibe la identificacion
     *  @param prmPassword Recibe la contrasenia
     * 
     *  @return Una instancia JSON con la clave 'login' en 'true' 
     *          si las credenciales son correctas. De lo contrario en 'false' 
     *          con su respectiva causa
     */
    public JSON login(Long prmIdentification, String prmPassword) throws Exception;

    /**
     *  Metodo para obtener un usuario por 'identification'
     *  
     *  @param prmIdentification Recibe la identificacion
     *  
     *  @return Una instancia del modelo con la informacion del usuario si existe.
     *          De lo contrario null
     */
    public UserModel getUserModel(Long prmIdentification) throws Exception;

    /**
     *  Metodo para obtener un usuario por 'identification'
     *  
     *  @param prmIdentification Recibe la identificacion
     *  
     *  @return Una instancia JSON con la clave 'getUser' en 'true' y con la
     *          informacion del usuario si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getUser(Long prmIdentification) throws Exception;

    /**
     *  Metodo para guardar un usuario
     * 
     *  @param prmUser Recibe los datos del usuario
     *  @param prmAvatar Recibe la imagen del avatar
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede guardar el usuario
     */
    public JSON save(UserModel prmUser, MultipartFile prmAvatar) throws Exception;

    /**
     *  Metodo para actualizar la contrasenia del usuario
     * 
     *  @param prmIdentification Recibe la identificacion
     *  @param prmPassword Recibe la nueva contrasenia
     * 
     *  @return Una instancia JSON con la clave 'update' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede actualizar la contrasenia
     */
    public JSON updatePassword(Long prmIdentification, String prmPassword) throws Exception;

    /**
     *  Metodo para eliminar un usuario 
     * 
     *  @param prmIdentification Recibe la identificacion
     * 
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON delete(Long prmIdentification) throws Exception;
}
