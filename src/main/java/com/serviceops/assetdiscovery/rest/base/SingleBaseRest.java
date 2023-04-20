package com.serviceops.assetdiscovery.rest.base;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A DTO for the {@link com.serviceops.assetdiscovery.entity.base.SingleBase} entity
 */
public class SingleBaseRest extends BaseRest implements Serializable {
    private String createdBy;
    private Timestamp createdTime;
    private String updatedBy;
    private Timestamp updatedTime;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SingleBaseRest entity = (SingleBaseRest) o;
        return Objects.equals(this.createdBy, entity.createdBy) && Objects.equals(this.createdTime,
                entity.createdTime) && Objects.equals(this.updatedBy, entity.updatedBy) && Objects.equals(
                this.updatedTime, entity.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdBy, createdTime, updatedBy, updatedTime);
    }

    @Override
    public String toString() {
        return "SingleBaseRest{" + "createdBy='" + createdBy + '\'' + ", createdTime=" + createdTime
                + ", updatedBy='" + updatedBy + '\'' + ", updatedTime=" + updatedTime + "} "
                + super.toString();
    }
}