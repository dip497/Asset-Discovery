package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import com.serviceops.assetdiscovery.entity.enums.TokenType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tokens extends SingleBase {
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;
    private boolean revoked;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Tokens tokens = (Tokens) o;

        if (isExpired() != tokens.isExpired())
            return false;
        if (isRevoked() != tokens.isRevoked())
            return false;
        if (getToken() != null ? !getToken().equals(tokens.getToken()) : tokens.getToken() != null)
            return false;
        if (getTokenType() != tokens.getTokenType())
            return false;
        return getUser() != null ? getUser().equals(tokens.getUser()) : tokens.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getToken() != null ? getToken().hashCode() : 0);
        result = 31 * result + (getTokenType() != null ? getTokenType().hashCode() : 0);
        result = 31 * result + (isExpired() ? 1 : 0);
        result = 31 * result + (isRevoked() ? 1 : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tokens{" + "token='" + token + '\'' + ", tokenType=" + tokenType + ", expired=" + expired
                + ", revoked=" + revoked + ", user=" + user + "} " + super.toString();
    }
}
