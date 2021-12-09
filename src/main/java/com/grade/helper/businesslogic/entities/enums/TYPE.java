package com.grade.helper.businesslogic.entities.enums;

public enum TYPE {
    TEST(1L, "Test"),
    PROJEKT(2L, "Projekt"),
    KLAUSUR(3L, "Klausur"),
    EPO(4L, "Epochalnote"),
    ;
    private final Long id;
    private final String value;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    TYPE(Long id, String value) {
        this.id = id;
        this.value = value;
    }
}
