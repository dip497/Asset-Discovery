package com.serviceops.assetdiscovery.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A DTO for the {@link com.serviceops.assetdiscovery.entity.Users} entity
 */
public class UsersDto extends BaseDto implements Serializable {
    private final String name;
    @Email
    private final String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\$\\@]).{6,}$")
    private final String password;

    public UsersDto(Long id, String createdBy, Timestamp createdTime, String updatedBy, Timestamp updatedTime, String name, String email, String password) {
        super(id, createdBy, createdTime, updatedBy, updatedTime);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDto entity = (UsersDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.password, entity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "email = " + email + ", " +
                "password = " + password + ")";
    }
}