package com.serviceops.assetdiscovery.rest.base;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.serviceops.assetdiscovery.entity.base.SingleBase} entity
 */
public class SingleBaseRest extends BaseRest implements Serializable {
    private long createdById;
    private Timestamp createdTime;
    private long updateById;
    private Timestamp updatedTime;

    public long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(long createdById) {
        this.createdById = createdById;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(long updateById) {
        this.updateById = updateById;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

}