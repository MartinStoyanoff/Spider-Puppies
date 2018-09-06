package com.spidermanteam.spiderpuppies.models.reporting;

public class UserResponse {

    private int id;
    private String userName;
    private String role;
    private String token;

    public UserResponse() {
    }

    public UserResponse(int id, String userName, String role, String token) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
