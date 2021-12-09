package com.grade.helper.businesslogic.entities.simple;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * created by ihelms on 25.11.2021
 */

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "grade_type", referencedColumnName = "id")
    private GradeType gradeType;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "prioritisation")
    private Double prioritisation;

    @Column(name = "date")
    private Timestamp date;

    @OneToOne
    @JoinColumn(name = "subject", referencedColumnName = "id")
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
                ", date=" + date +
                ", subject=" + subject +
                '}';
    }

    public Grade() {
    }

    public Grade(GradeType gradeType, Integer grade, Double prioritisation, Timestamp date, Subject subject) {
        this.gradeType = gradeType;
        this.grade = grade;
        this.prioritisation = prioritisation;
        this.date = date;
        this.subject = subject;
    }
}
