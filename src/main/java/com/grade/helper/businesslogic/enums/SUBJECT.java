package com.grade.helper.businesslogic.enums;

public enum SUBJECT {
    MATHEMATIK("Mathematik"),
    PHYSIK("Physik"),
    CHEMIE("Chemie"),
    ERDKUNDE("Erdkunde"),
    BIOLOGIE("Biologie"),
    DEUTSCH("Deutsch"),
    ENGLISCH("Englisch"),
    FRANZOESISCH("Franzoesisch"),
    SPANISCH("Spanisch"),
    LATEIN("Latein"),
    KUNST("Kunst"),
    BWL("Betriebswirtschaftslehre"),
    VWL("Volkswirtschaftslehre"),
    PSYCHOLOGIE("Psychologie"),
    SPORT("Sport");

    private String value;

    SUBJECT(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
