package com.grade.helper.businesslogic.entities.joined;

import com.grade.helper.businesslogic.entities.simple.*;

import javax.persistence.*;

/**
 * created by ihelms on 26.11.2021
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Table(name = "userGrade")
@Entity
public class UserGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userSchoolYearId", referencedColumnName = "id")
    private UserSchoolYear userSchoolYearId;

    @OneToOne
    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    private Grade gradeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserSchoolYear getUserSchoolYearId() {
        return userSchoolYearId;
    }

    public void setUserSchoolYearId(UserSchoolYear userSchoolYearId) {
        this.userSchoolYearId = userSchoolYearId;
    }

    public Grade getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grade gradeId) {
        this.gradeId = gradeId;
    }

    public UserGrade() {
    }

    public UserGrade(UserSchoolYear userSchoolYearId, Grade gradeId) {
        this.userSchoolYearId = userSchoolYearId;
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "UserGrade{" +
                "id=" + id +
                ", userSchoolYearId=" + userSchoolYearId +
                ", gradeId=" + gradeId +
                '}';
    }
}