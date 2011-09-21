package org.jasoet.mandiri.util.enums;


public enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    public static Month valueOf(int num) {
        switch (num) {
            case 0:
                return Month.JANUARY;
            case 1:
                return Month.FEBRUARY;
            case 2:
                return Month.MARCH;
            case 3:
                return Month.APRIL;
            case 4:
                return Month.MAY;
            case 5:
                return Month.JUNE;
            case 6:
                return Month.JULY;
            case 7:
                return Month.AUGUST;
            case 8:
                return Month.SEPTEMBER;
            case 9:
                return Month.OCTOBER;
            case 10:
                return Month.NOVEMBER;
            case 11:
                return Month.DECEMBER;
            default:
                return Month.JANUARY;
        }
    }

}
