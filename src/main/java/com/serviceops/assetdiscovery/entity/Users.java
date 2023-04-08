package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Users extends SingleBase {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;


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
