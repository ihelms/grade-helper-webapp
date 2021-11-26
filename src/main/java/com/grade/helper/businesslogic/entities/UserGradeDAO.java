package com.grade.helper.businesslogic.entities;

import javax.persistence.*;

/**
 * created by ihelms on 26.11.2021
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class UserGradeDAO {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserDAO userId;

    @OneToOne
    @JoinColumn(name = "schoolYearId", referencedColumnName = "id")
    private SchoolYearDAO schoolYearDAOId;

    @OneToOne
    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    private GradeDAO gradeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDAO getUserId() {
        return userId;
    }

    public void setUserId(UserDAO userId) {
        this.userId = userId;
    }

    public SchoolYearDAO getSchoolYearId() {
        return schoolYearDAOId;
    }

    public void setSchoolYearId(SchoolYearDAO schoolYearDAOId) {
        this.schoolYearDAOId = schoolYearDAOId;
    }

    public GradeDAO getGradeId() {
        return gradeId;
    }

    public void setGradeId(GradeDAO gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "UserGradeDAO{" +
                "id=" + id +
                ", userId=" + userId +
                ", schoolYearId=" + schoolYearDAOId +
                ", gradeId=" + gradeId +
                '}';
    }
}
