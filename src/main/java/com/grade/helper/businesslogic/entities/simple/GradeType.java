package com.grade.helper.businesslogic.entities.simple;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class GradeType {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GRADE_TYPE{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    public GradeType() {
    }

    public GradeType(Long id, String type) {
        this.id = id;
        this.type = type;
    }
}
