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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Users users = (Users) o;

        if (getName() != null ? !getName().equals(users.getName()) : users.getName() != null) return false;
        if (getEmail() != null ? !getEmail().equals(users.getEmail()) : users.getEmail() != null) return false;
        return getPassword() != null ? getPassword().equals(users.getPassword()) : users.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
