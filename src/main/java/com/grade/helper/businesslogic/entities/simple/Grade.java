package com.grade.helper.businesslogic.entities.simple;

import javax.persistence.*;

/**
 * created by ihelms on 25.11.2021
 */

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Table(name = "GRADE")
@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "GRADTYPEID", referencedColumnName = "ID")
    private GradeType gradeType;

    @Column(name = "GRADE")
    private Integer grade;

    @Column(name = "PRIORITISATION")
    private Double prioritisation;

    @OneToOne
    @JoinColumn(name = "SUBJECTID", referencedColumnName = "ID")
    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GradeType getGrade_type() {
        return gradeType;
    }

    public void setGrade_type(GradeType gradeType) {
        this.gradeType = gradeType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Double getPrioritisation() {
        return prioritisation;
    }

    public void setPrioritisation(Double prioritisation) {
        this.prioritisation = prioritisation;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "GradeDAO{" +
                "id=" + id +
                ", grade_type=" + gradeType +
                ", grade=" + grade +
                ", prioritisation=" + prioritisation +
                ", subject=" + subject +
                '}';
    }

    public Grade() {
    }

    public Grade(GradeType gradeType, Integer grade, Double prioritisation, Subject subject) {
        this.gradeType = gradeType;
        this.grade = grade;
        this.prioritisation = prioritisation;
        this.subject = subject;
    }
}
