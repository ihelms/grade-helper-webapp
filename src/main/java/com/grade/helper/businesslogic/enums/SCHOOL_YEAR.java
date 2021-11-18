package com.grade.helper.businesslogic.enums;

public enum SCHOOL_YEAR {
    FIRST_YEAR("1. Klasse"),
    SECOND_YEAR("2. Klasse"),
    THIRD_YEAR("3. Klasse"),
    FOURTH_YEAR("4. Klasse"),
    FIFTH_YEAR("5. Klasse"),
    SIXTH_YEAR("6. Klasse"),
    SEVENTH_YEAR("7. Klasse"),
    EIGHTH_YEAR("8. Klasse"),
    NINTH_YEAR("9. Klasse"),
    TENTH_YEAR("10. Klasse"),
    ELEVENTH_YEAR("11. Klasse"),
    TWELFTH_YEAR("12. Klasse"),
    THIRTEENTH_YEAR("13. Klasse");

    private String value;

    SCHOOL_YEAR(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
