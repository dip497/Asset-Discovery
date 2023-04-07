package com.serviceops.assetdiscovery.dto;

import com.serviceops.assetdiscovery.entity.mapped.Base;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A DTO for the {@link Base} entity
 */
public class BaseDto extends SingleBaseDto implements Serializable {
    private final String createdBy;
    private final Timestamp createdTime;
    private final String updatedBy;
    private final Timestamp updatedTime;


    public BaseDto(Long id, String createdBy, Timestamp createdTime, String updatedBy, Timestamp updatedTime) {
        super(id);
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDto entity = (BaseDto) o;
        return Objects.equals(this.createdBy, entity.createdBy) &&
                Objects.equals(this.createdTime, entity.createdTime) &&
                Objects.equals(this.updatedBy, entity.updatedBy) &&
                Objects.equals(this.updatedTime, entity.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, createdTime, updatedBy, updatedTime);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "createdBy = " + createdBy + ", " +
                "createdTime = " + createdTime + ", " +
                "updatedBy = " + updatedBy + ", " +
                "updatedTime = " + updatedTime + ")";
    }
}