package com.serviceops.assetdiscovery.entity.base;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AuditBase extends Base {
    private long createdById;
    private long createdTime;
    private long updatedById;
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

    public long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(long updatedById) {
        this.updatedById = updatedById;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        AuditBase that = (AuditBase) o;

        if (getCreatedById() != that.getCreatedById())
            return false;
        if (getCreatedTime() != that.getCreatedTime())
            return false;
        if (getUpdatedById() != that.getUpdatedById())
            return false;
        return getUpdatedTime() == that.getUpdatedTime();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (getCreatedById() ^ (getCreatedById() >>> 32));
        result = 31 * result + (int) (getCreatedTime() ^ (getCreatedTime() >>> 32));
        result = 31 * result + (int) (getUpdatedById() ^ (getUpdatedById() >>> 32));
        result = 31 * result + (int) (getUpdatedTime() ^ (getUpdatedTime() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AuditBase{" + "createdById=" + createdById + ", createdTime=" + createdTime
                + ", updatedById=" + updatedById + ", updatedTime=" + updatedTime + "} " + super.toString();
    }
}
