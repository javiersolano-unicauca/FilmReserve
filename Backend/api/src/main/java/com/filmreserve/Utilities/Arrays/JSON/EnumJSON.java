package com.filmreserve.Utilities.Arrays.JSON;

/**
*  @author javiersolanop
*/
public enum EnumJSON {
    
    STRING("String"),
    CHARACTER("Character"),
    INTEGER("Integer"),
    LONG("Long"),
    FLOAT("Float"),
    DOUBLE("Double"),
    BOOLEAN("Boolean"),
    JSON("JSON"),
    LINKED_LIST_JSON("LinkedListJSON");

    // Properties:
    private String atrTypeData;

    // Constructors:
    private EnumJSON(String prmTypeData)
    {
        atrTypeData = prmTypeData;
    }

    // Methods:
    public String getTypeData(){ return atrTypeData; }
}
