package com.filmreserve.Libraries.Arrays.Lists;

import com.filmreserve.Libraries.Exceptions.ListException;
import java.util.Iterator;

/**
 *  Clase para listas super enlazadas
 * 
 *  @param <K> recibe el tipo de clave
 *  @param <V> recibe el tipo de valor que almacena
 * 
 *  @author javiersolanop
 */
public class SuperLinkedList<K, V> implements Iterable<ElementContent<K, V>> {
    
    private Element<K, V> atrFirst;
    private Element<K, V> atrLast;
    private int atrSize;
    
    /**
     *  Constructor
     */
    public SuperLinkedList()
    {
        atrFirst = null;
        atrLast = null;
        atrSize = 0;
    }
    
    /**
    *  Metodo para agregar un valor al principio de la lista
    * 
    *  @param prmKey Recibe la clave
    *  @param prmValue Recibe la copia del valor
    */
    public void addFirst(K prmKey, V prmValue)
    {
        if(atrLast != null)
        {
            Element<K, V> ptrNewElement = new Element(prmKey, prmValue);
            ptrNewElement.setRight(atrFirst);
            atrFirst.setLeft(ptrNewElement);
            atrFirst = ptrNewElement;
        }
        else{
            atrLast = new Element(prmKey, prmValue);
            atrFirst = atrLast;
        }
        atrSize++;
    }
    
    /**
    *  Metodo para agregar un valor en la lista
    * 
    *  @param prmKey Recibe la clave
    *  @param prmValue Recibe la copia del valor
    */
    public void add(K prmKey, V prmValue)
    {
        if(atrFirst != null)
        {
            Element<K, V> ptrNewElement = new Element(prmKey, prmValue);
            ptrNewElement.setLeft(atrLast);
            atrLast.setRight(ptrNewElement);
            atrLast = ptrNewElement;
        }
        else{
            atrFirst = new Element(prmKey, prmValue);
            atrLast = atrFirst;
        }
        atrSize++;
    }
    
    /**
    *  Metodo para validar si la lista contiene una clave especifica
    * 
    *  @param prmKey Recibe la clave
    * 
    *  @return 'true' si contiene la clave.
    *          'false' si no
    */
    public boolean contains(K prmKey)
    {
        Element<K, V> ptrElement = atrFirst;

        while((ptrElement != null) && (ptrElement.getKey() != prmKey))
            ptrElement = ptrElement.getRight();

        return (ptrElement != null);
    }
    
    /**
    *  Metodo para obtener el valor correspondiente
    *  a una clave en la lista.
    * 
    *  @param prmKey Recibe la clave del valor a obtener
    * 
    *  @return El valor de esa clave si existe. De lo contrario null
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */
    public V get(K prmKey) throws ListException
    {
        ListException.throwException((atrFirst == null), ListException.EMPTY);

        Element<K, V> ptrElement = atrFirst;        

        while((ptrElement != null) && (ptrElement.getKey() != prmKey))
            ptrElement = ptrElement.getRight();

        if(ptrElement == null) return null;
        return ptrElement.getValue();
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
    *  de una clave en la lista
    * 
    *  @param prmFind Recibe la clave a encontrar
    *  @param prmValue Recibe la copia del valor a reemplazar
    * 
    *  @return 'true' si se actualiza el valor.
    *          'false' si no
    */
    public boolean update(K prmFind, V prmValue)
    {
        Element<K, V> ptrElement = atrFirst;

        while((ptrElement != null) && (ptrElement.getKey() != prmFind))
            ptrElement = ptrElement.getRight();

        if(ptrElement == null)
            return false;

        ptrElement.setValue(prmValue);
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

        Element<K, V> ptrElement = null;

        if(atrFirst.getRight() != null)
        {
            atrFirst.getRight().setLeft(null);
            ptrElement = atrFirst.getRight();
        }
        else
            atrLast = null;

        atrFirst = ptrElement;
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

        Element<K, V> ptrElement = null;

        if(atrLast.getLeft() != null)
        {
            atrLast.getLeft().setRight(null);
            ptrElement = atrLast.getLeft();
        }
        else
            atrFirst = null;

        atrLast = ptrElement;
        atrSize--;
    }
    
    /**
    *  Metodo para remover la primera coincidencia de 
    *  una clave que no se encuentre en algun extremo de la lista
    * 
    *  @param prmKey Recibe la clave a eliminar
    * 
    *  @return 'true' si se elimina el valor.
    *          'false' si no
    */
    public boolean remove(K prmKey)
    {
        if((atrFirst.getKey() == prmKey) || (atrLast.getKey() == prmKey))
            return false;

        Element<K, V> ptrElement = atrFirst;

        while((ptrElement != null) && (ptrElement.getKey() != prmKey))
            ptrElement = ptrElement.getRight();

        if(ptrElement == null)
            return false;

        ptrElement.getLeft().setRight(ptrElement.getRight());
        ptrElement.getRight().setLeft(ptrElement.getLeft());

        atrSize--;
        return true;
    }
    
    /**
    *  Metodo para romover todos los valores de la lista
    */
    public void clear()
    {
        Element<K, V> ptrLeft;

        while(atrLast != null)
        {
            ptrLeft = atrLast.getLeft();
            atrLast = ptrLeft;
        }
        atrFirst = null;
    }
    
    /**
    *  Metodo para obtener el valor de la primera clave de la lista
    * 
    *  @return El valor
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */
    public V getFirst() throws ListException
    {
        ListException.throwException((atrFirst == null), ListException.EMPTY);
        return atrFirst.getValue();
    }
    
    /**
    *  Metodo para obtener el valor de la primera clave de la lista
    * 
    *  @return El valor
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */
    public V getLast() throws ListException
    {
        ListException.throwException((atrLast == null), ListException.EMPTY);
        return atrLast.getValue();
    }
    
    /**
    *  Metodo para validar si la clave recibida tiene la primera posicion en la lista
    * 
     * @param prmKey Recibe la clave a validar
     * 
    *  @return 'true' si tienen la primera posicion. 'false' si no
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */
    public boolean isFirst(K prmKey) throws ListException
    {
        ListException.throwException((atrFirst == null), ListException.EMPTY);
        return (atrFirst.getKey() == prmKey);
    }
    
    /**
    *  Metodo para validar si la clave recibida tiene la ultima posicion en la lista
    * 
    *  @param prmKey Recibe la clave a validar
    * 
    *  @return 'true' si tienen la ultima posicion. 'false' si no
    * 
    *  @throws Libraries.Exceptions.ListException Si la lista esta vacia
    */
    public boolean isLast(K prmKey) throws ListException
    {
        ListException.throwException((atrLast == null), ListException.EMPTY);
        return (atrLast.getKey() == prmKey);
    }

    @Override
    public Iterator iterator() {
        return new SuperListIterator(atrFirst);
    }
}
