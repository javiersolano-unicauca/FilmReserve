package com.filmreserve.Utilities.Arrays.Lists;

import java.util.Iterator;

import com.filmreserve.Utilities.Arrays.JSON.iJSON;

public class ListIteratorJSON implements Iterator<iJSON>{

    Node<iJSON> atrLink;

    public ListIteratorJSON(Node<iJSON> prmLink)
    {
        atrLink = prmLink;
    }

    @Override
    public boolean hasNext() {
        return atrLink != null;  
    }

    @Override
    public iJSON next() {
        iJSON varValue = atrLink.getValue();
        atrLink = atrLink.getRight();
        return varValue;
    }
}
