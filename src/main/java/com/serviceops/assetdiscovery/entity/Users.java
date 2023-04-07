package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.sql.Timestamp;
@Entity
public class Users extends Base{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public Users(String createdBy, Timestamp createdTime, String updatedBy, Timestamp updatedTime, String name, String email, String password) {
        super(createdBy, createdTime, updatedBy, updatedTime);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    protected Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
