package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.enums.Role;
import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Users} entity
 */
public class UsersRest extends SingleBaseRest implements Serializable {
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        UsersRest usersRest = (UsersRest) o;

        if (getName() != null ? !getName().equals(usersRest.getName()) : usersRest.getName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(usersRest.getEmail()) : usersRest.getEmail() != null)
            return false;
        if (getPassword() != null ?
                !getPassword().equals(usersRest.getPassword()) :
                usersRest.getPassword() != null)
            return false;
        return getRole() == usersRest.getRole();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsersRest{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", password='" + password
                + '\'' + ", role=" + role + "} " + super.toString();
    }
}