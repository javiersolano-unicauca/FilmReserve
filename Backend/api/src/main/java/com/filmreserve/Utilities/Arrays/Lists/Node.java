 package com.filmreserve.Utilities.Arrays.Lists;

/**
 *  @param <T> Tipo de elemento
 * 
 *  @author javiersolanop
 */
public class Node<T> {
    
    private Node<T> atrLeft;
    private Node<T> atrRight;
    private T atrValue;

    public Node()
    {
        atrLeft = null;
        atrRight = null;
        atrValue = null;
    }

    public Node(T prmValue)
    {
        atrLeft = null;
        atrRight = null;
        atrValue = prmValue;
    }
    
    public Node(Node<T> prmRight, T prmValue)
    {
        atrRight = prmRight;
        atrValue = prmValue;
    }
    
    public Node(Node<T> prmLeft, Node<T> prmRight)
    {
        atrLeft = prmLeft;
        atrRight = prmRight;
    }
    
    public Node(Node<T> prmLeft, Node<T> prmRight, T prmValue)
    {
        atrLeft = prmLeft;
        atrRight = prmRight;
        atrValue = prmValue;
    }

    public Node<T> getRight()
    {
        return atrRight;
    }

    public void setRight(Node<T> prmNode)
    {
        atrRight = prmNode;
    }
    
    public Node<T> getLeft()
    {
        return atrLeft;
    }
    
    public void setLeft(Node<T> prmNode)
    {
        atrLeft = prmNode;
    }

    public T getValue()
    {
        return atrValue;
    }

    public void setValue(T prmValue)
    {
        atrValue = prmValue;
    }
}
