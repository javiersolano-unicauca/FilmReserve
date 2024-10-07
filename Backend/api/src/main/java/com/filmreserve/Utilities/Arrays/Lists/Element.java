package com.filmreserve.Utilities.Arrays.Lists;

/**
 *  @param <K> tipo de clave.
 *  @param <V> tipo de valor.
 * 
 *  @author javiersolanop
 */
public class Element<K, V> {

    private Element<K, V> atrLeft;
    private Element<K, V> atrRight;
    private K atrKey;
    private V atrValue;

    public Element()
    {
        atrLeft = null;
        atrRight = null;
        atrKey = null;
        atrValue = null;
    }

    public Element(K prmKey, V prmValue)
    {
        atrLeft = null;
        atrRight = null;
        atrKey = prmKey;
        atrValue = prmValue;
    }

    public Element<K, V> getRight()
    {
        return atrRight;
    }

    public void setRight(Element<K, V> prmElement)
    {
        atrRight = prmElement;
    }
    
    public Element<K, V> getLeft()
    {
        return atrLeft;
    }
    
    public void setLeft(Element<K, V> prmElement)
    {
        atrLeft = prmElement;
    }
    
    public K getKey()
    {
        return atrKey;
    }

    public void setKey(K prmKey)
    {
        atrKey = prmKey;
    }

    public V getValue()
    {
        return atrValue;
    }

    public void setValue(V prmValue)
    {
        atrValue = prmValue;
    }
}
