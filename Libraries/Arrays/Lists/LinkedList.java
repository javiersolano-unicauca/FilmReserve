package com.filmreserve.Libraries.Arrays.Lists;

import com.filmreserve.Libraries.Exceptions.ListException;
import java.util.Iterator;

/**
 *  Clase para listas enlazadas
 * 
 *  @param <T> Tipo de dato a almacenar
 * 
 *  @author javiersolanop
*/
public class LinkedList<T> implements Iterable<T> {
    
    private Node<T> atrFirst;
    private Node<T> atrLast;
    private int atrSize;
    
    /**
     *  Constructor
     */
    public LinkedList()
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
    public void addFirst(T prmValue)
    {
        if(atrLast != null)
        {
            Node<T> ptrNewNode = new Node(prmValue);
            ptrNewNode.setRight(atrFirst);
            atrFirst.setLeft(ptrNewNode);
            atrFirst = ptrNewNode;
        }
        else{
            atrLast = new Node(prmValue);
            atrFirst = atrLast;
        }
        atrSize++;
    }
    
    /**
    *  Metodo para agregar un valor en la lista
    * 
    *  @param prmValue Recibe la copia del valor
    */
    public void add(T prmValue)
    {
        if(atrFirst != null)
        {
            Node<T> ptrNewNode = new Node(prmValue);
            ptrNewNode.setLeft(atrLast);
            atrLast.setRight(ptrNewNode);
            atrLast = ptrNewNode;
        }
        else{
            atrFirst = new Node(prmValue);
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
    public boolean contains(T prmValue)
    {
        Node<T> ptrNode = atrFirst;

        while((ptrNode != null) && (ptrNode.getValue() != prmValue))
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
    public T get(int prmPosition) throws ListException
    {
        ListException.throwException((atrFirst == null), ListException.EMPTY);
        ListException.throwException((prmPosition < 0), ListException.POSITION_NEGATIVE);
        ListException.throwException(((prmPosition == 0) || (prmPosition > atrSize)), 
                                        ListException.POSITION_OUT_OF_RANGE);

        Node<T> ptrNode = atrFirst;        

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
    public boolean update(T prmFind, T prmValue)
    {
        Node<T> ptrNode = atrFirst;

        while((ptrNode != null) && (ptrNode.getValue() != prmFind))
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

        Node<T> ptrNode = null;

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

        Node<T> ptrNode = null;

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
    public boolean remove(T prmValue)
    {
        if((atrFirst.getValue() == prmValue) || (atrLast.getValue() == prmValue))
            return false;

        Node<T> ptrNode = atrFirst;

        while((ptrNode != null) && (ptrNode.getValue() != prmValue))
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
        Node<T> ptrLeft;

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
    public T getFirst() throws ListException
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
    public T getLast() throws ListException
    {
        ListException.throwException((atrLast == null), ListException.EMPTY);
        return atrLast.getValue();
    }

    @Override
    public Iterator iterator() {
        return new ListIterator(atrFirst);
    }
}
