package com.assignment.blogplatform.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "role_name", nullable = false)
    private String roleName;


    public Role() {
    }

    public Role(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

