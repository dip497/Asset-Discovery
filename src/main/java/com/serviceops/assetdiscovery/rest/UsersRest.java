package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Users} entity
 */
public class UsersRest extends SingleBaseRest implements Serializable {
    private String name;
    private String email;
    private String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UsersRest entity = (UsersRest) o;
        return Objects.equals(this.name, entity.name) && Objects.equals(this.email, entity.email)
                && Objects.equals(this.password, entity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "name = " + name + ", " + "email = " + email + ", "
                + "password = " + password + ")";
    }
}