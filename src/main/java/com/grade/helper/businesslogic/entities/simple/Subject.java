package com.grade.helper.businesslogic.entities.simple;

import javax.persistence.*;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Table(name = "subject")
@Entity
public class Subject {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Subject() {
    }

    public Subject(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "SUBJECT{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
