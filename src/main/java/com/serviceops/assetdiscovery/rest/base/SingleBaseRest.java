package com.serviceops.assetdiscovery.rest.base;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.serviceops.assetdiscovery.entity.base.SingleBase} entity
 */
public class SingleBaseRest extends BaseRest implements Serializable {
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