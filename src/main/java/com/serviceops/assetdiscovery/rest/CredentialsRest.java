package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Credentials} entity
 */
public class CredentialsRest extends SingleBaseRest implements Serializable {
    private  String username;
    private  String password;
    private  String ipAddress;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialsRest entity = (CredentialsRest) o;
        return Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.ipAddress, entity.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, ipAddress);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "ipAddress = " + ipAddress + ")";
    }
}