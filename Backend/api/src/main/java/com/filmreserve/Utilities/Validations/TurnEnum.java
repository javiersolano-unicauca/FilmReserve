package com.filmreserve.Utilities.Validations;

public enum TurnEnum {

    AFTERNOON("TARDE"),
    EVENING("NOCHE");

    // Properties:
    private String atrTypeData;

    // Constructors:
    private TurnEnum(String prmTypeData)
    {
        atrTypeData = prmTypeData;
    }

    // Methods:
    public String getTypeData(){ return atrTypeData; }
}
