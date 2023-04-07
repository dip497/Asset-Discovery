package com.serviceops.assetdiscovery.dto;

import com.serviceops.assetdiscovery.entity.mapped.SingleBase;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SingleBase} entity
 */
public class SingleBaseDto implements Serializable {
    private final Long id;

    public SingleBaseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleBaseDto entity = (SingleBaseDto) o;
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