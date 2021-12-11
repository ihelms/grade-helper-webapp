package com.grade.helper.businesslogic.entities.simple;

import javax.persistence.*;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Table(name = "GRADETYPE")
@Entity
public class GradeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "GRADETYPE")
    private String gradeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String type) {
        this.gradeType = type;
    }

    @Override
    public String toString() {
        return "GRADE_TYPE{" +
                "id=" + id +
                ", type='" + gradeType + '\'' +
                '}';
    }

    public GradeType() {
    }

    public GradeType(Long id, String gradeType) {
        this.id = id;
        this.gradeType = gradeType;
    }
}
