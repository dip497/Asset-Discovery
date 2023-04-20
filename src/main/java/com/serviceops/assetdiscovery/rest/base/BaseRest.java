package com.serviceops.assetdiscovery.rest.base;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.serviceops.assetdiscovery.entity.base.Base} entity
 */
public class BaseRest implements Serializable {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BaseRest baseRest = (BaseRest) o;

        return getId() == baseRest.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        return "BaseRest{" + "id=" + id + '}';
    }
}