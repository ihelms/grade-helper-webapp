package com.grade.helper.businesslogic.entities.enums;

public enum YEAR {
    FIRST(1L, "1. Klasse"),
    SECOND(2L, "2. Klasse"),
    THIRD(3L, "3. Klasse"),
    FOURTH(4L, "4. Klasse"),
    FIFTH(5L, "5. Klasse"),
    SIXTH(6L, "6. Klasse"),
    SEVENTH(7L, "7. Klasse"),
    EIGHTH(8L, "8. Klasse"),
    NINTH(9L, "9. Klasse"),
    TENTH(10L, "10. Klasse"),
    ELEVENTH(11L, "11. Klasse"),
    TWELFTH(12L, "12. Klasse"),
    THIRTEENTH(13L, "13. Klasse");

    private final Long id;
    private final String value;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    YEAR(Long id, String value) {
        this.id = id;
        this.value = value;
    }
}
