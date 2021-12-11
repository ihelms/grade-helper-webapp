package com.grade.helper.businesslogic.entities.enums;

public enum SUBJECT {
    BWL(1L, "Betriebswirtschaftslehre"),
    VWL(2L, "Volkswirtschaftslehre"),
    IV(3L, "Informationsverarbeitung"),
    MATHE(4L, "Mathematik"),
    BIO(5L, "Biologie"),
    CHEMIE(6L, "Chemie"),
    PHYSIK(7L, "Physik"),
    ENGLISCH(8L, "Englisch"),
    ETHIK(9L, "Ethik"),
    RELI(10L, "Religion"),
    DEUTSCH(11L, "Deutsch"),
    SPANISCH(12L, "Spanisch"),
    FRANZOESISCH(13L, "Franzoesisch"),
    KUNST(14L, "Kunst"),
    SPORT(15L, "Sport")
    ;

    private final Long id;
    private final String value;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    SUBJECT(Long id, String value) {
        this.id = id;
        this.value = value;
    }
}
