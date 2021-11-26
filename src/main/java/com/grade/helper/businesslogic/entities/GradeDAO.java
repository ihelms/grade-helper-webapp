package com.grade.helper.businesslogic.entities;

import com.grade.helper.businesslogic.enums.GRADE_TYPE;
import com.grade.helper.businesslogic.enums.SUBJECT;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * created by ihelms on 25.11.2021
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class GradeDAO {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "grade_type", referencedColumnName = "id")
    private GRADE_TYPE grade_type;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "prioritisation")
    private Double prioritisation;

    @Column(name = "date")
    private Timestamp date;

    @OneToOne
    @JoinColumn(name = "subject", referencedColumnName = "id")
    private SUBJECT subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GRADE_TYPE getGrade_type() {
        return grade_type;
    }

    public void setGrade_type(GRADE_TYPE grade_type) {
        this.grade_type = grade_type;
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

    public SUBJECT getSubject() {
        return subject;
    }

    public void setSubject(SUBJECT subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "GradeDAO{" +
                "id=" + id +
                ", grade_type=" + grade_type +
                ", grade=" + grade +
                ", prioritisation=" + prioritisation +
                ", date=" + date +
                ", subject=" + subject +
                '}';
    }
}
