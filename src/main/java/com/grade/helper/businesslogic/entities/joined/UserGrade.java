package com.grade.helper.businesslogic.entities.joined;

import com.grade.helper.businesslogic.entities.simple.*;

import javax.persistence.*;

/**
 * created by ihelms on 26.11.2021
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class UserGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    @OneToOne
    @JoinColumn(name = "schoolYearId", referencedColumnName = "id")
    private SchoolYear schoolYearId;

    @OneToOne
    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    private Grade gradeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public SchoolYear getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(SchoolYear schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public Grade getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grade gradeId) {
        this.gradeId = gradeId;
    }

    public UserGrade() {
    }

    public UserGrade(User userId, SchoolYear schoolYearId, Grade gradeId) {
        this.userId = userId;
        this.schoolYearId = schoolYearId;
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "UserGradeDAO{" +
                "id=" + id +
                ", userId=" + userId +
                ", schoolYearId=" + schoolYearId +
                ", gradeId=" + gradeId +
                '}';
    }
}
