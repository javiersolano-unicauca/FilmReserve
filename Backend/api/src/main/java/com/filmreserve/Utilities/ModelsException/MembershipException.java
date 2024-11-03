package com.filmreserve.Utilities.ModelsException;

/**
 * @author javiersolanop
 */
public class MembershipException extends ModelException {

    // Properties of class:
    public static final int IDENTIFICATION = 1;
    public static final int START_DATE = 2;
    public static final int END_DATE = 3;

    // Constructors:

    public MembershipException()
    {}

    public MembershipException(int prmField, Exception prmException)
    {
        super(prmField, prmException);
    }

    // Methods:

    @Override
    protected String getFieldString(int prmField) {
        
        String varField = "";

        switch (prmField)
        { 
            case IDENTIFICATION: varField = "identification"; break;
            case START_DATE: varField = "startDate"; break;
            case END_DATE: varField = "endDate"; break;
        }
        return varField;
    }

    public static void throwException(int prmField, Exception prmException) throws MembershipException
    {
        throw new MembershipException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, int prmField, Exception prmException) throws MembershipException
    {
        if(prmCondition) throw new MembershipException(prmField, prmException);
    }
}
