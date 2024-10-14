package com.filmreserve.Utilities.Arrays.JSON;

import com.filmreserve.Utilities.Arrays.ChainOfCharacter.ChainOfCharacter;
import com.filmreserve.Utilities.Arrays.Lists.ElementContent;
import com.filmreserve.Utilities.Arrays.Lists.LinkedList;
import com.filmreserve.Utilities.Arrays.Lists.LinkedListJSON;
import com.filmreserve.Utilities.Arrays.Lists.SuperLinkedList;
import com.filmreserve.Utilities.Exceptions.ChainOfCharacterException;
import com.filmreserve.Utilities.Exceptions.JSONException;
import com.filmreserve.Utilities.Exceptions.ListException;
 
/**
*  @author javiersolanop
*/
public class JSON  {
    
    // Properties:
    private final SuperLinkedList<String, Object> atrJSON;

    // Constructors:
    public JSON(){ atrJSON = new SuperLinkedList<>(); }

    // Methods of object:
    
     /**
     *  Metodo para validar los tipos de caracteres de un valor de tipo 'String'
     * 
     * @param prmValue Recibe el valor
     * @return 'true' si los tipos de caracteres son permitidos. 'false' si no.
     */
    private boolean validateStringValue(String prmValue)
    {
        for(int i = 0; i < prmValue.length(); i++){

            if(((prmValue.charAt(i) < 32) || (prmValue.charAt(i) > 126)) && (prmValue.charAt(i) == 0))
                return false;
        }
        return true;
    }

    /**
     *  Metodo para validar el tipo de dato del valor recibido.
     * 
     * @param prmValue Recibe el valor
     * @return 'true' si el tipo de dato es valido. 'false' si no.
     */
    private boolean validateTypeValue(Object prmValue)
    {
        for(EnumJSON objTypeData: EnumJSON.values()){

            if(objTypeData.getTypeData().equals(prmValue.getClass().getSimpleName())){

                if(prmValue instanceof String)
                    return validateStringValue((String)prmValue);
                return true;
            }
        }
        return false;
    }

    /**
     *  Metodo para adicionar una clave y su valor.El tipo de dato del valor debe ser compatible con los
     *  definidos en el 'EnumJSON':
     *  @see EnumJSON
     * 
     *  @param prmKey Recibe la clave
     *  @param prmValue Recibe el valor
     *  
     *  @throws Libraries.Exceptions.JSONException Si el tipo de valor no es valido o ya existe esa clave
     */
    public void add(String prmKey, Object prmValue) throws JSONException, ListException
    {
        JSONException.throwException(!validateTypeValue(prmValue), JSONException.NOT_DATA_VALID);
        
        if(atrJSON.size() > 0)
            JSONException.throwException(atrJSON.get(prmKey) != null, JSONException.KEY_DUPLICATED);
        
        atrJSON.add(prmKey, prmValue);
    }

    /**
     *  Metodo para obtener el valor de una clave especifica.
     * 
     *  @param prmKey Recibe la clave
     *  @return El valor, si existe la clave. De lo contrario null
     *  
     *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
     */
    public Object get(String prmKey) throws ListException
    {
        return atrJSON.get(prmKey);
    }

    /**
     *  Metodo para actualizar una clave especifica.
     * 
     * @param prmKey Recibe la clave
     * @param prmValue Recibe el nuevo valor
     * @return 'true' si se actualiza. 'false' si no existe esa clave.
     */
    public boolean update(String prmKey, Object prmValue)
    {
       return atrJSON.update(prmKey, prmValue);
    }

    /**
     *  Metodo para remover una clave especifica.
     * 
     *  @param prmKey Recibe la clave
     * 
     *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
     */
    public void remove(String prmKey) throws ListException
    {
        if(atrJSON.isFirst(prmKey)) atrJSON.removeFirst();
      
        else if(atrJSON.isLast(prmKey)) atrJSON.removeLast();
        
        else atrJSON.remove(prmKey);
    }

    /**
     *  Metodo para validar si el objeto esta vacio.
     * 
     * @return 'true' si esta vacio. 'false' si no
     */
    public boolean isEmpty(){ return atrJSON.isEmpty(); }

    /**
     *  Metodo para obtener el tamanio del objeto
     * 
     * @return El tamanio
     */
    public int size(){ return atrJSON.size(); }

    @Override
    public String toString()
    {
        int varCount = 1, varSize = atrJSON.size();
        String varJSON = "{";

        for(ElementContent<String, Object> objJSON: atrJSON){
            
            varJSON += "\""+objJSON.getKey()+"\":";

            if(objJSON.getValue() instanceof String)
                varJSON += "\""+objJSON.getValue()+"\"";
            
            else if(objJSON.getValue() instanceof JSON)
                varJSON += objJSON.getValue().toString();

            else varJSON += objJSON.getValue();

            if(varCount < varSize)
                varJSON += ",";
            varCount++;
        }

        varJSON += "}";
        return varJSON;
    }

    /**
     *  Metodo para convertir el objeto a String con formato 'JSON'
     *  @return El String de 'JSON' con formato.
     */
    public String toFormattedString()
    {
        int varCount = 1, varSize = atrJSON.size();
        String varJSON = "{\n";

        for(ElementContent<String, Object> objJSON: atrJSON){
            
            varJSON += "  \""+objJSON.getKey()+"\":";
            varJSON += (objJSON.getValue() instanceof String) ? "\""+objJSON.getValue()+"\"" : objJSON.getValue();

            if(varCount < varSize)
                varJSON += ",";
            varJSON += "\n";
            varCount++;
        }

        varJSON += "}";
        return varJSON;
    }

    /**
     *  Metodo para validar si el objeto tiene exactamente
     *  el mismo contenido que otro
     * 
     *  @param prmJson Recibe el objeto a validar
     * 
     *  @return 'true' si tienen el mismo contenido. 'false' si no
     */
    public boolean equals(JSON prmJson)
    {
        return atrJSON.equals(prmJson.atrJSON);
    }

    /**
     *  Metodo para eliminar todas las claves.
     */
    public void clear()
    {
        atrJSON.clear();
    }

    // Methods of class:

    /**
     *  Metodo para validar el tipo de dato de un valor que se encuentra en un String
     * 
     *  @param prmValue Recibe el valor
     *  
     *  @return El tipo de dato.
     */
    private static Object validateTypeData(String prmValue)
    {
        if(ChainOfCharacter.isNumberInt(prmValue))
            return ChainOfCharacter.parseNumberInt(prmValue);

        else if(ChainOfCharacter.isNumberReal(prmValue))
            return ChainOfCharacter.parseNumberReal(prmValue);

        else if(ChainOfCharacter.isBoolean(prmValue))
            return Boolean.parseBoolean(prmValue);

        return prmValue;
    }

    /**
     *  Metodo para parsear un String de 'JSON' a objeto
     * 
     *  @param prmJSON Recibe el String de 'JSON'
     *  @return El objeto
     * 
     *  @throws Libraries.Exceptions.JSONException Si el tipo de valor no es valido o hay duplicacion de claves
     *  @throws Libraries.Exceptions.ListException Si no hay claves
     *  @throws Libraries.Exceptions.ChainOfCharacterException Si algun valor de tipo numero no es valido
     */
    public static JSON parseJSON(String prmJSON) throws JSONException, ListException, ChainOfCharacterException
    {
        JSON objJSON = new JSON();
        String varJSON = prmJSON.replace("{", "")
                                .replace("}", "")
                                .replace("\"", "");

        String[] arrJSON = varJSON.split(","),
                 arrRow;

        for(String row: arrJSON){
            arrRow = row.split(":");
            objJSON.add(arrRow[0], validateTypeData(arrRow[1]));
        }
        return objJSON;
    }

    /**
     *  Metodo para parsear un arreglo de String de 'JSON' a de objetos.
     * 
     *  @param prmJSON Recibe el arreglo de String de 'JSON'
     *  @return El arreglo de objetos 
     * 
     *  @throws Libraries.Exceptions.JSONException Si el tipo de valor no es valido o hay duplicacion de claves
     *  @throws Libraries.Exceptions.ListException Si no hay claves
     *  @throws Libraries.Exceptions.ChainOfCharacterException Si algun valor de tipo numero no es valido
     */
    public static LinkedList<JSON> parseJSON(String[] prmJSON) throws JSONException, ListException, ChainOfCharacterException
    {
        String[] arrJSON = prmJSON.clone();
        LinkedList<JSON> arrObjJsons = new LinkedList<>();

        arrJSON[0] = arrJSON[0].replace("[", "");
        arrJSON[arrJSON.length - 1] = arrJSON[arrJSON.length - 1].replace("]", "");

        for(String objJson: arrJSON)
            arrObjJsons.add(JSON.parseJSON(objJson.replace("},", "}")));
        return arrObjJsons;
    }
 
    /**
     *  Metodo para convertir el arreglo de objetos a de String de 'JSON'
     * 
     *  @param prmJsons Recibe el arreglo de objetos
     *  @return  El arreglo de String de 'JSON'
     * 
     *  @throws Libraries.Exceptions.ListException Si hay un error en la lista de claves
     */
    public static String[] toStringJSON(LinkedList<JSON> prmJsons) throws Exception
    {   
        String[] arrJson = {};
                
        if(!prmJsons.isEmpty())
        {
            int varSize = prmJsons.size(), 
            varIterador = varSize - 1;

            arrJson = new String[varSize];

            if(varSize > 1)
            {
                for (JSON objJson : prmJsons) {

                    if(varIterador == 0)
                        arrJson[varIterador] = "["+objJson.toString();
                    else
                        arrJson[varIterador] = objJson.toString();

                    if(varIterador < (varSize - 1))
                        arrJson[varIterador] += ",";
                    varIterador--;
                }
            }    
            else
                arrJson[0] = "["+prmJsons.getFirst().toString();

            arrJson[varSize - 1] += "]";
        }
        return arrJson;
    }

    /**
     *  Metodo para convertir el arreglo de objetos a de String de 'JSON'
     * 
     *  @param prmJsons Recibe el arreglo de objetos
     *  @return  El arreglo de String de 'JSON'
     * 
     *  @throws Libraries.Exceptions.ListException Si hay un error en la lista de claves
     */
    public static String[] toStringJSON(LinkedListJSON prmJsons) throws Exception
    {   
        String[] arrJson = {};
                
        if(!prmJsons.isEmpty())
        {
            int varSize = prmJsons.size(), 
            varIterador = varSize - 1;

            arrJson = new String[varSize];

            if(varSize > 1)
            {
                for (iJSON objJson : prmJsons) {

                    if(varIterador == 0)
                        arrJson[varIterador] = "["+objJson.toJSON().toString();
                    else
                        arrJson[varIterador] = objJson.toJSON().toString();

                    if(varIterador < (varSize - 1))
                        arrJson[varIterador] += ",";
                    varIterador--;
                }
            }    
            else
                arrJson[0] = "["+prmJsons.getFirst().toJSON().toString();

            arrJson[varSize - 1] += "]";
        }
        return arrJson;
    }
}
