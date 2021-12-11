package com.grade.helper.businesslogic.entities.simple;

import javax.persistence.*;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Table(name = "SCHOOLYEAR")
@Entity
public class SchoolYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "YEAR")
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

    public SchoolYear() {
    }

    public SchoolYear(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "SchoolYearDAO{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
