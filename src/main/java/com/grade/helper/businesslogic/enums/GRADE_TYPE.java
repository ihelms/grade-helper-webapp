package com.grade.helper.businesslogic.enums;

import javax.persistence.*;

@Entity
public enum GRADE_TYPE {
    KLAUSUR,
    PROJEKT,
    EPO,
    TEST;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
