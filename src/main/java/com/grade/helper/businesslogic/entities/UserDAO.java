package com.grade.helper.businesslogic.entities;

import javax.persistence.*;

/**
 * created by ihelms on 18.11.2021
 */

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
public class UserDAO {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
