package com.grade.helper.businesslogic.entities.joined;

import com.grade.helper.businesslogic.entities.simple.SchoolYear;
import com.grade.helper.businesslogic.entities.simple.User;

import javax.persistence.*;

/**
 * created by ihelms on 10.12.2021
 */

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Table(name = "USERSCHOOLYEAR")
@Entity
public class UserSchoolYear {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    private User userId;

    @OneToOne
    @JoinColumn(name = "YEARID", referencedColumnName = "ID")
    private SchoolYear schoolYearId;

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

    public UserSchoolYear() {
    }

    public UserSchoolYear(User userId, SchoolYear schoolYearId) {
        this.userId = userId;
        this.schoolYearId = schoolYearId;
    }

    @Override
    public String toString() {
        return "UserSubject{" +
                "id=" + id +
                ", userId=" + userId +
                ", schoolYearId=" + schoolYearId +
                '}';
    }
}
