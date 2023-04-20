package com.serviceops.assetdiscovery.entity.base;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@MappedSuperclass
public class SingleBase extends Base {
    private long createdById;
    @CreationTimestamp
    private Timestamp createdTime;
    private long updatedById;
    @UpdateTimestamp
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

    public long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(long updatedById) {
        this.updatedById = updatedById;
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
        if (!super.equals(o))
            return false;

        SingleBase that = (SingleBase) o;

        if (getCreatedById() != that.getCreatedById())
            return false;
        if (getUpdatedById() != that.getUpdatedById())
            return false;
        if (getCreatedTime() != null ?
                !getCreatedTime().equals(that.getCreatedTime()) :
                that.getCreatedTime() != null)
            return false;
        return getUpdatedTime() != null ?
                getUpdatedTime().equals(that.getUpdatedTime()) :
                that.getUpdatedTime() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (getCreatedById() ^ (getCreatedById() >>> 32));
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (int) (getUpdatedById() ^ (getUpdatedById() >>> 32));
        result = 31 * result + (getUpdatedTime() != null ? getUpdatedTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SingleBase{" + "createdById=" + createdById + ", createdTime=" + createdTime
                + ", updatedById=" + updatedById + ", updatedTime=" + updatedTime + "} " + super.toString();
    }
}
