package com.filmreserve.Utilities.Arrays.Lists;

/**
 *  Clase para el contenido de un elemento
 * 
 *  @param <K> tipo de clave.
 *  @param <V> tipo de valor.
 * 
 *  @author javiersolanop
 */
public class ElementContent<K, V> {
    
    private K atrKey;
    private V atrValue;
    
    public ElementContent(K prmKey, V prmValue)
    {
        atrKey = prmKey;
        atrValue = prmValue;
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
