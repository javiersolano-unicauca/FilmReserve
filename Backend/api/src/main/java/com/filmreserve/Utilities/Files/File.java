package com.filmreserve.Utilities.Files;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.filmreserve.Utilities.Exceptions.FileException;

/**
 * @author javiersolanop
 */
public class File {
    
    // Properties:
    private String atrPath = System.getProperty("user.dir");
    private FileInputStream atrFileInput;
    private FileOutputStream atrFileOutput;

    // Constructors:
    public File(String prmPath)
    {
        atrPath += "\\" + prmPath;
    }

    // Methods:

    /**
     *  Metodo para exportar un archivo en formato 'txt',
     *  adicionando un salto de linea a cada fila del arreglo.
     * 
     *  @param prmFileName El nombre que debe tener el archivo
     *  @param prmContent El contenido del archivo
     * 
     *  @throws FileException Si no se puede exportar
     */
    public void exportTxtWithLn(String prmFileName, String[] prmContent) throws FileException
    {
        try{

            atrFileOutput = new FileOutputStream(atrPath+"\\"+prmFileName+".txt");
            String[] varContent = prmContent.clone();

            for(String str: varContent){
                str = str.concat("\n");
                atrFileOutput.write(str.getBytes(StandardCharsets.UTF_8));
            }
            atrFileOutput.flush();
            atrFileOutput.close();

        }catch(IOException e){
            FileException.throwException(FileException.CANNOT_EXPORT);
        }
    }
    
    /**
     *  Metodo para exportar un archivo en formato 'txt'
     * 
     *  @param prmFileName El nombre que debe tener el archivo
     *  @param prmContent El contenido del archivo
     * 
     *  @throws FileException Si no se puede exportar
     */
    public void exportTxt(String prmFileName, String[] prmContent) throws FileException
    {
        try{

            atrFileOutput = new FileOutputStream(atrPath+"\\"+prmFileName+".txt");
            String[] varContent = prmContent.clone();

            for(String str: varContent)
                atrFileOutput.write(str.getBytes(StandardCharsets.UTF_8));
            
            atrFileOutput.flush();
            atrFileOutput.close();

        }catch(IOException e){
            FileException.throwException(FileException.CANNOT_EXPORT);
        }
    }

    /**
     *  Metodo para exportar un archivo en formato 'jpeg'
     * 
     *  @param prmFileName El nombre que debe tener el archivo
     *  @param prmImage El contenido del archivo
     * 
     *  @throws FileException Si no se puede exportar
     */
    public void exportJpeg(String prmFileName, byte[] prmImage) throws FileException
    {
        try{
            atrFileOutput = new FileOutputStream(atrPath + "\\" + prmFileName + ".jpeg");

            atrFileOutput.write(prmImage);
            atrFileOutput.flush();
            atrFileOutput.close();

        }catch(IOException e){
            FileException.throwException(FileException.CANNOT_EXPORT);
        }
    }

    /**
     *  Metodo para importar un archivo en formato 'txt'
     * 
     *  @param prmFileName El nombre del archivo
     *  @return El arreglo de filas del archivo o null si esta vacio
     * 
     *  @throws FileException Si no se puede importar
     */
    public String[] importTxt(String prmFileName) throws FileException
    {
        try{

            atrFileInput = new FileInputStream(atrPath+"\\"+prmFileName+".txt");
            byte[] arrBytes = new byte[atrFileInput.available()];
            DataInputStream objDataInputStream = new DataInputStream(atrFileInput);
            objDataInputStream.readFully(arrBytes);
            atrFileInput.close();
            
            if(arrBytes.length > 0){
                
                byte[][] mtrBytes = Byte.splitln(arrBytes);
                String[] arrRows;

                if(mtrBytes != null){
                   
                    int varSize = mtrBytes.length;
                    arrRows = new String[varSize];

                    for(int i = 0; i < varSize; i++)
                        arrRows[i] = Byte.parseByteToString(mtrBytes[i]);
                }else{
                    arrRows = new String[1];
                    arrRows[0] = Byte.parseByteToString(arrBytes).replace("\n", "");
                }

                return arrRows;
            }

        }catch(IOException e){
            FileException.throwException(FileException.CANNOT_IMPORT);
        }
        return null;
    }

    /**
     *  Metodo para remover un archivo en formato 'jpeg'
     * 
     *  @param prmFileName El nombre del archivo
     * 
     *  @throws FileException Si no se puede remover
     */
    public void removeJpeg(String prmFileName) throws FileException
    {
        try{

            java.io.File objFile = new java.io.File(atrPath+"\\"+prmFileName+".jpeg");

            if(!objFile.delete()) FileException.throwException(FileException.CANNOT_DELETE);

        }catch(Exception e){
            FileException.throwException(FileException.CANNOT_DELETE);
        }
    }
}
