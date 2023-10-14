package com.assignment.blogplatform.models;

public class UserModel {

    private Long userId;
    private String userName;
    private String password;
    private String email;
    private boolean active;
    private String roleName;

    public UserModel() {
    }

    public UserModel(Long userId, String userName, String password, String email, boolean active, String roleName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.active = active;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
