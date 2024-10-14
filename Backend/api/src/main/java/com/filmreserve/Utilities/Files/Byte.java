package com.filmreserve.Utilities.Files;

/**
 *  @author javiersolanop
 */
public class Byte {
    
    /**
     *  Metodo para parsear un arreglo de bytes a su respectiva 
     *  representacion en caracteres.
     *
     *  @param prmBytes Recibe el arreglo de bytes
     *  @return Los caracteres que representa
     */
    public static String parseByteToString(byte[] prmBytes)
    {
        String varChain = "";
        
        for(byte b: prmBytes){
            
            if(b != 0)
                varChain += (char)b;
        }
        return varChain;
    }

    /**
     *  Metodo para contar la cantidad de veces que se repite un byte
     *  en un arreglo de bytes.
     * 
     *  @param prmBytes Recibe el arreglo de bytes
     *  @param prmByte Recibe el byte a buscar
     *  @return La cantidad de veces que se repite
     */
    public static int numberOfRepetitions(byte[] prmBytes, byte prmByte)
    {
        int varCount = 0;

        for(byte b: prmBytes){

            if(b == prmByte)
                varCount++;
        }
        return varCount;
    }

    /**
     *  Metodo para dividir un arreglo de bytes en subarreglos.
     *  Tomando el salto de linea como divisor.
     * 
     *  @param prmBytes Recibe el arreglo de bytes
     *  @return Los subarreglos
     */
    public static byte[][] splitln(byte[] prmBytes)
    {
        byte varDivider = (byte)10;
        int varNumberOfArrays = numberOfRepetitions(prmBytes, varDivider) - 1;

        if((varNumberOfArrays > 0) && ((prmBytes[0] != varDivider) && (prmBytes[prmBytes.length - 2] != varDivider))){

            byte[][] mtrBytes = new byte[varNumberOfArrays + 1][prmBytes.length - 2];
            int i = 0, j = 0, k = 0;

            while(i < prmBytes.length){

                if(prmBytes[i] != varDivider){

                    mtrBytes[j][k] = prmBytes[i];
                    k++;

                }else{
                    j++;
                    k = 0;
                }
                i++;
            }
            return mtrBytes;
        }
        return null;
    }
}
