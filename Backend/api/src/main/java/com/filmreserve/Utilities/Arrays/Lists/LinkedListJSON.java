package com.filmreserve.Utilities.Arrays.Lists;

import java.util.Iterator;

import com.filmreserve.Utilities.Arrays.JSON.JSON;
import com.filmreserve.Utilities.Arrays.JSON.iJSON;
import com.filmreserve.Utilities.Exceptions.ListException;

/**
 *  Clase para listas enlazadas de objetos 'JSON'
 *  @see JSON
 * 
 *  @author javiersolanop
*/
public class LinkedListJSON implements Iterable<iJSON> {

    private Node<iJSON> atrFirst;
    private Node<iJSON> atrLast;
    private int atrSize;
    
    /**
     *  Constructor
     */
    public LinkedListJSON()
    {
        atrFirst = null;
        atrLast = null;
        atrSize = 0;
    }
    
    /**
    *  Metodo para agregar un valor al principio de la lista
    * 
    *  @param prmValue Recibe la copia del valor
    */
    public void addFirst(iJSON prmValue)
    {
        if(atrLast != null)
        {
            Node<iJSON> ptrNewNode = new Node<>(prmValue);
            ptrNewNode.setRight(atrFirst);
            atrFirst.setLeft(ptrNewNode);
            atrFirst = ptrNewNode;
        }
        else{
            atrLast = new Node<>(prmValue);
            atrFirst = atrLast;
        }
        atrSize++;
    }
    
    /**
    *  Metodo para agregar un valor en la lista
    * 
    *  @param prmValue Recibe la copia del valor
    */
    public void add(iJSON prmValue)
    {
        if(atrFirst != null)
        {
            Node<iJSON> ptrNewNode = new Node<>(prmValue);
            ptrNewNode.setLeft(atrLast);
            atrLast.setRight(ptrNewNode);
            atrLast = ptrNewNode;
        }
        else{
            atrFirst = new Node<>(prmValue);
            atrLast = atrFirst;
        }
        atrSize++;
    }
    
    /**
    *  Metodo para validar si la lista contiene un valor especifico
    * 
    *  @param prmValue Recibe la copia del valor
    * 
    *  @return 'true' si contiene el valor.
    *          'false' si no
    */
    public boolean contains(iJSON prmValue) throws Exception
    {
        Node<iJSON> ptrNode = atrFirst;

        while((ptrNode != null) && (!ptrNode.getValue().equalsJSON(prmValue.toJSON())))
            ptrNode = ptrNode.getRight();

        return (ptrNode != null);
    }
    
    /**
    *  Metodo para obtener el valor correspondiente
    *  a la posicion en la lista.
    * 
    *  @param prmPosition Recibe la posicion del valor a obtener
    * 
    *  @return El valor en esa posicion.
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia,
    *                                             la posicion es negativa 
    *                                             o posicion fuera de rango.
    */
    public iJSON get(int prmPosition) throws ListException
    {
        ListException.throwException((atrFirst == null), ListException.EMPTY);
        ListException.throwException((prmPosition < 0), ListException.POSITION_NEGATIVE);
        ListException.throwException(((prmPosition == 0) || (prmPosition > atrSize)), 
                                        ListException.POSITION_OUT_OF_RANGE);

        Node<iJSON> ptrNode = atrFirst;        

        for(int i = 1; i < prmPosition; i++)
            ptrNode = ptrNode.getRight();

        return ptrNode.getValue();
    }
    
    /**
     * Metodo para validar si la lista esta vacia
     * 
     * @return 'true' si lo esta. 'false' si no
     */
    public boolean isEmpty()
    {
        return (atrSize == 0);
    }
    
    /**
    *  Metodo para obtener el tamanio de la lista
    * 
    *  @return El tamanio
    */
    public int size()    
    {
        return atrSize;
    }
    
    /**
    *  Metodo para actualizar la primera coincidencia 
    *  de un valor en la lista
    * 
    *  @param prmFind Recibe la copia del valor a encontrar
    *  @param prmValue Recibe la copia del valor a reemplazar
    * 
    *  @return 'true' si se actualiza el valor.
    *          'false' si no
    */
    public boolean update(iJSON prmFind, iJSON prmValue) throws Exception
    {
        Node<iJSON> ptrNode = atrFirst;

        while((ptrNode != null) && (!ptrNode.getValue().equals(prmFind)))
            ptrNode = ptrNode.getRight();

        if(ptrNode == null)
            return false;

        ptrNode.setValue(prmValue);
        return true;
    }
    
    /**
    *  Metodo para eliminar el primer elemento de la lista.
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */
    public void removeFirst() throws ListException
    {
        ListException.throwException((atrFirst == null), ListException.EMPTY);

        Node<iJSON> ptrNode = null;

        if(atrFirst.getRight() != null)
        {
            atrFirst.getRight().setLeft(null);
            ptrNode = atrFirst.getRight();
        }
        else
            atrLast = null;

        atrFirst = ptrNode;
        atrSize--;
    }
    
    /**
    *  Metodo para eliminar el ultimo elemento de la lista.
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */       
    public void removeLast() throws ListException
    {
        ListException.throwException((atrLast == null), ListException.EMPTY);

        Node<iJSON> ptrNode = null;

        if(atrLast.getLeft() != null)
        {
            atrLast.getLeft().setRight(null);
            ptrNode = atrLast.getLeft();
        }
        else
            atrFirst = null;

        atrLast = ptrNode;
        atrSize--;
    }
    
    /**
    *  Metodo para remover la primera coincidencia de 
    *  un valor que no se encuentre en algun extremo de la lista
    * 
    *  @param prmValue Recibe la copia del valor a eliminar
    * 
    *  @return 'true' si se elimina el valor.
    *          'false' si no
    */
    public boolean remove(iJSON prmValue) throws Exception
    {
        iJSON varValue = prmValue;

        if((atrFirst.getValue().equals(varValue)) || (atrLast.getValue().equals(varValue)))
            return false;

        Node<iJSON> ptrNode = atrFirst;

        while((ptrNode != null) && (!ptrNode.getValue().equals(varValue)))
            ptrNode = ptrNode.getRight();

        if(ptrNode == null)
            return false;

        ptrNode.getLeft().setRight(ptrNode.getRight());
        ptrNode.getRight().setLeft(ptrNode.getLeft());

        atrSize--;
        return true;
    }
    
    /**
    *  Metodo para romover todos los valores de la lista
    */
    public void clear()
    {
        Node<iJSON> ptrLeft;

        while(atrLast != null)
        {
            ptrLeft = atrLast.getLeft();
            atrLast = ptrLeft;
        }
        atrFirst = null;
    }
    
    /**
    *  Metodo para obtener el primer valor de la lista
    * 
    *  @return El valor
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */
    public iJSON getFirst() throws ListException
    {
        ListException.throwException((atrFirst == null), ListException.EMPTY);
        return atrFirst.getValue();
    }
    
    /**
    *  Metodo para obtener el ultimo valor de la lista
    * 
    *  @return El valor
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */
    public iJSON getLast() throws ListException
    {
        ListException.throwException((atrLast == null), ListException.EMPTY);
        return atrLast.getValue();
    }

    /**
     *  Metodo para obtener la lista de strings de 'JSON'
     * 
     *  @return La lista de strings o null si esta vacia
     */
    @Override
    public String toString()
    {
        try{
            return JSON.toStringJSON(this);
        }catch(Exception e){ return null; }
    }

    @Override
    public Iterator iterator() {
        return new ListIteratorJSON(atrFirst);
    }
}
