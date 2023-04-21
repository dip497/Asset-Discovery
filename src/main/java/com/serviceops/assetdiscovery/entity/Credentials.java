package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AuditBase;
import com.serviceops.assetdiscovery.entity.enums.CredentialType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Credentials extends AuditBase {
    private String username;
    private String password;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private CredentialType credentialType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CredentialType getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(CredentialType credentialType) {
        this.credentialType = credentialType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Credentials that = (Credentials) o;

        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if (getDescription() != null ?
                !getDescription().equals(that.getDescription()) :
                that.getDescription() != null)
            return false;
        return getCredentialType() == that.getCredentialType();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getCredentialType() != null ? getCredentialType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Credentials{" + "username='" + username + '\'' + ", password='" + password + '\''
                + ", description='" + description + '\'' + ", credentialType=" + credentialType + "} "
                + super.toString();
    }
}
