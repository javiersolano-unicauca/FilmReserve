package com.filmreserve.Utilities.Arrays.ChainOfCharacter;

import com.filmreserve.Utilities.Exceptions.ChainOfCharacterException;

/**
 * @author javiersolanop
 */
public class ChainOfCharacter {

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
