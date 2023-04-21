package com.serviceops.assetdiscovery.rest.base;

import com.serviceops.assetdiscovery.entity.base.AuditBase;

import java.io.Serializable;

/**
 * A DTO for the {@link AuditBase} entity
 */
public abstract class AuditBaseRest extends BaseRest implements Serializable {
    private long createdById;
    private long createdTime;
    private long updateById;
    private long updatedTime;

    public long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(long createdById) {
        this.createdById = createdById;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(long updateById) {
        this.updateById = updateById;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

}