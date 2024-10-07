package com.filmreserve.Libraries.Arrays.Lists;

import java.util.Iterator;

/**
 * Clase para iterar elementos de una lista generica 
 * 
 * @param <T> Tipo de elemento
 * 
 * @author javiersolanop
 */
public class ListIterator<T> implements Iterator<T>{

    Node<T> atrLink;

    public ListIterator(Node<T> prmLink)
    {
        atrLink = prmLink;
    }

    @Override
    public boolean hasNext() {
        return atrLink != null;  
    }

    @Override
    public T next() {
        T varValue = atrLink.getValue();
        atrLink = atrLink.getRight();
        return varValue;
    }
}
