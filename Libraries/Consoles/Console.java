 package com.filmreserve.Libraries.Consoles;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.filmreserve.Libraries.Exceptions.MathException;

/**
*  @author javiersolanop
*/
public class Console {

    /**
     *  Metodo para imprimir una cadena en consola.
     * 
     *  @param prmChain Recibe la cadena.
     */
    public static void println(String prmChain)
    {
        System.out.println(prmChain);
    }

    /**
     *  Metodo para imprimir un objeto en consola.
     * 
     *  @param prmObject Recibe el objeto.
     */
    public static void println(Object prmObject)
    {
        System.out.println(prmObject);
    }

    /**
     *  Metodo para imprimir una cadena en consola sin salto de linea al final.
     * 
     *  @param  prmChain Recibe la cadena.
     */
    public static void print(String prmChain)
    {
        System.out.print(prmChain);
    }

    /**
     *  Metodo para imprimir una cadena de caracteres en consola,
     *  aniade salto de linea inicial y final.
     * 
     *  @param prmMessage Recibe la cadena.
     */
    public static void printMessage(String prmMessage)
    {
        System.out.println("\n"+prmMessage+"\n");
    }

    /**
     *  Metodo para imprimir un titulo en consola,
     *  se imprime de la siguiente manera: ----- prmTitle -----
     * 
     *  @param prmTitle Recibe el titulo.
     */
    public static void titlePrint(String prmTitle)
    {
        printMessage("----- "+prmTitle+" -----");
    }
    
    /**
     *  Metodo para recibir entradas de tipo cadena por consola.
     * 
     *  @param prmDescription Recibe la descripcion de la entrada que se solicita.
     * 
     *  @return La entrada ingresada por el usuario.
     */
    public static String input(String prmDescription)
    {
        printMessage(prmDescription+":");
        return new Scanner(System.in).nextLine();
    }

    /**
     *  Metodo para recibir entradas de tipo entero por consola.
     * 
     *  @param prmDescription Recibe la descripcion de la entrada que se solicita.
     * 
     *  @return La entrada ingresada por el usuario.
     *  @throws MathException En el caso de que el dato ingresado por el usuario no corresponde a un numero entero.
     */
    public static int integerInput(String prmDescription) throws MathException
    {
        printMessage(prmDescription+":");
        Scanner objScanner = new Scanner(System.in);

        try{
            return objScanner.nextInt();
        }catch(InputMismatchException e){
            throw new MathException(MathException.NOT_INTEGER);
        }
    }
    
    /**
     *  Metodo para recibir entradas de tipo float por consola.
     * 
     *  @param prmDescription Recibe la descripcion de la entrada que se solicita.
     * 
     *  @return La entrada ingresada por el usuario.
     *  @throws MathException En el caso de que el dato ingresado por el usuario no corresponde a un numero real
     */
    public static float floatInput(String prmDescription) throws MathException
    {
        printMessage(prmDescription+":");
        Scanner objScanner = new Scanner(System.in);

        try{
            return objScanner.nextFloat();
        }catch(InputMismatchException e){
            throw new MathException(MathException.NOT_DOUBLE);
        }
    }

    /**
     *  Metodo para recibir entradas de tipo double por consola.
     * 
     *  @param prmDescription Recibe la descripcion de la entrada que se solicita.
     * 
     *  @return La entrada ingresada por el usuario.
     *  @throws MathException En el caso de que el dato ingresado por el usuario no corresponde a un numero real
     */
    public static double doubleInput(String prmDescription) throws MathException
    {
        printMessage(prmDescription+":");
        Scanner objScanner = new Scanner(System.in);

        try{
            return objScanner.nextDouble();
        }catch(InputMismatchException e){
            throw new MathException(MathException.NOT_DOUBLE);
        }
    }
}


