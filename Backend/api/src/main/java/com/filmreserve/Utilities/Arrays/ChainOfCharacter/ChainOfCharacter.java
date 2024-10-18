package com.filmreserve.Utilities.Arrays.ChainOfCharacter;

import com.filmreserve.Utilities.Exceptions.ChainOfCharacterException;

/**
 * @author javiersolanop
 */
public class ChainOfCharacter {

    /**
     *  Metodo para convertir una cadena de caracteres en arreglo.
     * 
     *  Ejemplo del patron permitido:
     *  <blockquote>
     *      <pre> [apple, pear, strawberry] </pre>
     *  </blockquote>
     * 
     *  @param prmChain Recibe la cadena
     * 
     *  @return El arreglo o null si la cadena esta vacia
     * 
     *  @throws ChainOfCharacterException Si no tiene el patron permitido
     */
    public static String[] toArray(String prmChain) throws ChainOfCharacterException
    {
        if(prmChain.isEmpty() || prmChain.endsWith("[]")) return null;

        int varEnd = prmChain.length() - 1;

        ChainOfCharacterException.throwException(
            ((prmChain.charAt(0) != '[') || (prmChain.charAt(varEnd) != ']')),
            ChainOfCharacterException.NOT_PATTERN
        );

        try{
            return prmChain.substring(1, varEnd)
                            .replaceAll(" ", "")
                            .split(",");
        }catch(Exception e)
        {
            ChainOfCharacterException.throwException(true, ChainOfCharacterException.NOT_PATTERN);
        }
        return null;
    }

    /**
     *  Metodo para extraer una subcadena de caracteres
     * 
     *  @param prmChain Recibe la cadena
     *  @param prmLimit Recibe el caracter que finaliza la extraccion
     * 
     *  @return La subcadena o null si la cadena es nula
     */
    public static String substring(String prmChain, Character prmLimit)
    {
        if(prmChain == null) return null;

        int varLenght = prmChain.length();
        String varSustring = "";


        for(int i = 0; i < varLenght; i++)
        {
            if(prmChain.charAt(i) != prmLimit) 
                varSustring += prmChain.charAt(i);
            else break;
        }

        return varSustring;
    }

    /**
     *  Metodo para validar si la candena contiene solamente letras
     * 
     *  @param prmChain Recibe la cadena
     * 
     *  @throws ChainOfCharacterException Si no contiene solamente letras
     */
    public static void containsLettersWithException(String prmChain) throws ChainOfCharacterException
    {
        if(prmChain == null) return;

        for(int i = 0; i < prmChain.length(); i++){
            
            if(((prmChain.charAt(i) < 'A') || (prmChain.charAt(i) > 'Z')) &&
              ((prmChain.charAt(i) < 'a') || (prmChain.charAt(i) > 'z')) &&
              (prmChain.charAt(i) != ' '))
            {
                ChainOfCharacterException.throwException(true, ChainOfCharacterException.NOT_CONTAINS_LETTERS);
            }
        }
    }

    /**
    *  Metodo para validar si la candena contiene solamente letras
    * 
    * @param prmChain Recibe la cadena
    * @return 'true' si solo contiene letras. 'false' si no.
    */
    public static boolean containsLetters(String prmChain)
    {
        if(prmChain == null) return false;

        for(int i = 0; i < prmChain.length(); i++){
            
            if(((prmChain.charAt(i) < 'A') || (prmChain.charAt(i) > 'Z')) &&
                ((prmChain.charAt(i) < 'a') || (prmChain.charAt(i) > 'z')) &&
                (prmChain.charAt(i) != ' '))
                    return false;
        }
        return true;
    }
    
    /**
     *  Metodo para validar si la cadena es un numero real
     * 
     *  @param prmChain Recibe la cadena
     * 
     *  @throws ChainOfCharacterException Si no es un numero real 
     */
    public static void isNumberRealWithException(String prmChain) throws ChainOfCharacterException
    {
        if(prmChain == null) return;

        ChainOfCharacterException.throwException(
            ((prmChain.charAt(0) != '-') || ((prmChain.charAt(0) >= '0') && (prmChain.charAt(0) <= '9'))), 
            ChainOfCharacterException.NOT_NUMBER_REAL
        );

        for(int i = 1; i < prmChain.length(); i++){

            if(((prmChain.charAt(i) < '0') || (prmChain.charAt(i) > '9')) && (prmChain.charAt(i) != '.'))
                ChainOfCharacterException.throwException(true, ChainOfCharacterException.NOT_NUMBER_REAL);
        }
    }

    /**
     *  Metodo para validar si la cadena es un numero real
     * 
     *  @param prmChain Recibe la cadena
     *  @return 'true' si es un numero real. 'false' si no.
     */
    public static boolean isNumberReal(String prmChain)
    {
        if(prmChain == null) return false;

        if((prmChain.charAt(0) == '-') || ((prmChain.charAt(0) >= '0') && (prmChain.charAt(0) <= '9'))){

            for(int i = 1; i < prmChain.length(); i++){

                if(((prmChain.charAt(i) < '0') || (prmChain.charAt(i) > '9')) && (prmChain.charAt(i) != '.'))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     *  Metodo para validar si la cadena es un numero entero
     * 
     *  @param prmChain Recibe la cadena
     * 
     *  @throws ChainOfCharacterException Si no es un numero entero 
     */
    public static void isNumberIntWithException(String prmChain) throws ChainOfCharacterException
    {
        if(prmChain == null) return;

        ChainOfCharacterException.throwException(
            ((prmChain.charAt(0) != '-') || ((prmChain.charAt(0) >= '0') && (prmChain.charAt(0) <= '9'))), 
            ChainOfCharacterException.NOT_NUMBER_INTEGER
        );

        for(int i = 1; i < prmChain.length(); i++){

            if((prmChain.charAt(i) < '0') || (prmChain.charAt(i) > '9'))
                ChainOfCharacterException.throwException(true, ChainOfCharacterException.NOT_NUMBER_INTEGER);
        }
    }

    /**
     *  Metodo para validar si la cadena es un numero entero
     * 
     *  @param prmChain Recibe la cadena
     *  @return 'true' si es un numero entero. 'false' si no. 
     */
    public static boolean isNumberInt(String prmChain)
    {
        if(prmChain == null) return false;

        if((prmChain.charAt(0) == '-') || ((prmChain.charAt(0) >= '0') && (prmChain.charAt(0) <= '9'))){

            for(int i = 1; i < prmChain.length(); i++){

                if((prmChain.charAt(i) < '0') || (prmChain.charAt(i) > '9'))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     *  Metodo para validar si la cadena es un booleano
     * 
     *  @param prmChain Recibe la cadena
     *  @return 'true' si es un booleano. 'false' si no. 
     */
    public static boolean isBoolean(String prmChain)
    {
        if(prmChain == null) return false;
        return ((prmChain.equals("true")) || (prmChain.equals("false")));
    }

    /**
     *  Metodo para parsear una cadena a numero entero
     * 
     *  @param prmChain Recibe la cadena
     *  @return El numero parseado o null si la cadena no es un numero entero.
     */
    public static Object parseNumberInt(String prmChain)
    {
        if(isNumberInt(prmChain)){

            try{
                return Integer.parseInt(prmChain);
            }catch(NumberFormatException e){
                return Long.parseLong(prmChain);
            }
        }
        return null;
    }
    
    /**
     *  Metodo para parsear una cadena a numero real
     * 
     *  @param prmChain Recibe la cadena
     *  @return El numero parseado o null si la cadena no es un numero real.
     */
    public static Object parseNumberReal(String prmChain)
    {
        if(isNumberReal(prmChain)){

            try{
                return Float.parseFloat(prmChain);
            }catch(NumberFormatException e){
                return Double.parseDouble(prmChain);
            }
        }
        return null;
    }
}
