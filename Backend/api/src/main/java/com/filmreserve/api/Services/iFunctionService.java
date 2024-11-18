package com.filmreserve.api.Services;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.api.Models.FunctionModel;
import com.filmreserve.api.Models.FunctionPK;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;

/**
 * @author javiersolanop
 */
public interface iFunctionService {

    /**
     *  Metodo para obtener una funcion por 'FunctionPK'
     *  
     *  @param prmIdMovie   Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  
     *  @return Una instancia del modelo con la informacion de la funcion si existe.
     *          De lo contrario null
     */
    public FunctionModel getFunctionModel(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para validar si existe una funcion
     * 
     *  @param prmIdMovie   Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  
     *  @return 'true' si existe. De lo contrario 'false'
     */
    public boolean exists(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para validar si existe una funcion activa
     * 
     *  @param prmIdMovie   Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  
     *  @return 'true' si existe. De lo contrario 'false'
     */
    public boolean existsActive(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para obtener una pelicula por 'FunctionPK'
     *  
     *  @param prmIdMovie   Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     *  
     *  @return Una instancia JSON con la clave 'getFunction' en 'true' y con la
     *          informacion de la funcion si existe. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON getFunction(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para obtener todas las funciones
     * 
     *  @return La lista con funciones
     * 
     *  @throws Exception Si no hay funciones
     */
    public List<FunctionModel> all() throws Exception;

    /**
     *  Metodo para guardar una funcion
     * 
     *  @param prmFunctionPK Recibe el id
     * 
     *  @return Una instancia JSON con la clave 'save' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede guardar la funcion
     */
    public JSON save(FunctionPK prmFunctionPK) throws Exception;

    /**
     *  Metodo para actualizar el poster de una pelicula
     *  
     *  @param prmIdMovie   Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     * 
     *  @return Una instancia JSON con la clave 'functionEnd' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     * 
     *  @see JSON
     *  @throws Exception Si no se puede actualizar el poster
     */
    public JSON functionEnd(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;

    /**
     *  Metodo para eliminar una funcion
     * 
     *  @param prmIdMovie   Recibe el id de la pelicula
     *  @param prmStartDate Recibe la fecha de inicio
     *  @param prmStartTime Recibe la hora de inicio
     * 
     *  @return Una instancia JSON con la clave 'delete' en 'true'. De lo contrario en 'false'
     *          con su respectiva causa
     *  @see JSON
     */
    public JSON delete(
        Long prmIdMovie,
        LocalDate prmStartDate,
        LocalTime prmStartTime
    ) throws Exception;
}
