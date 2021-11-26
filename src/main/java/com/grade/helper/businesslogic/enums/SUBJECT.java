package com.grade.helper.businesslogic.enums;

import javax.persistence.*;

@Entity
public enum SUBJECT {
    MATHEMATIK,
    PHYSIK,
    CHEMIE,
    ERDKUNDE,
    BIOLOGIE,
    DEUTSCH,
    ENGLISCH,
    FRANZOESISCH,
    SPANISCH,
    LATEIN,
    KUNST,
    BWL,
    VWL,
    PSYCHOLOGIE,
    SPORT;

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
