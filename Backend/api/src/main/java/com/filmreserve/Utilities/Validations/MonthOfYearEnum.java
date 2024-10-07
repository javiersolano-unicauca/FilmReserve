package com.filmreserve.Utilities.Validations;

public enum MonthOfYearEnum {

    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    // Properties:
    private Integer atrTypeData;

    // Constructors:
    private MonthOfYearEnum(Integer prmTypeData)
    {
        atrTypeData = prmTypeData;
    }

    // Methods:
    public Integer getTypeData(){ return atrTypeData; }
}
