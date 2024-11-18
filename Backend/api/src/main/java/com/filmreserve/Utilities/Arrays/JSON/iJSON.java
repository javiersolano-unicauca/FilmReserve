package com.filmreserve.Utilities.Arrays.JSON;

/**
 *  Interfaz para mapear clases en objetos de la clase 'JSON'
 *  @see JSON
 * 
 *  @author javiersolanop
 */
public interface iJSON {

    /**
     *  Metodo para obtener una instancia de 'JSON' con la 
     *  informacion de la clase
     */
    public JSON toJSON() throws Exception;

    /**
     *  Metodo para validar si el objeto tiene exactamente
     *  el mismo contenido que otro
     * 
     *  @param prmJson Recibe el objeto a validar
     * 
     *  @return 'true' si tienen el mismo contenido. 'false' si no
     */
    public boolean equalsJSON(JSON prmJson) throws Exception;
}
