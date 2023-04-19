package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Keyboard} entity
 */
public class KeyboardRest extends AssetBaseRest implements Serializable {
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        KeyboardRest that = (KeyboardRest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "KeyboardRest{" +
                "name='" + name + '\'' +
                '}';
    }
}