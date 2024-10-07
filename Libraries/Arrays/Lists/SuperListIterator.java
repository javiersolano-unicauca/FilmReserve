package com.filmreserve.Libraries.Arrays.Lists;

import java.util.Iterator;

/**
 * Clase para iterar elementos de una super lista generica 
 * 
 * @param <K> Tipo de clave
 * @param <V> Tipo de valor
 * 
 * @author javiersolanop
 */
public class SuperListIterator<K, V> implements Iterator<ElementContent<K, V>> {
    
    Element<K, V> atrLink;

    public SuperListIterator(Element<K, V> prmLink)
    {
        atrLink = prmLink;
    }

    @Override
    public boolean hasNext() {
        return atrLink != null;  
    }

    @Override
    public ElementContent<K, V> next() {
        ElementContent<K, V> varElement = new ElementContent(atrLink.getKey(), atrLink.getValue());
        atrLink = atrLink.getRight();
        return varElement;
    }
}
