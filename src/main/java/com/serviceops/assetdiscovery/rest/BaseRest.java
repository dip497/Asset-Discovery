package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.serviceops.assetdiscovery.entity.mapped.Base} entity
 */
public class BaseRest implements Serializable {
    private  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseRest entity = (BaseRest) o;
        return Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}