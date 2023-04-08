package com.serviceops.assetdiscovery.entity.base;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
@MappedSuperclass
public class SingleBase extends Base {
    private String createdBy;
    @CreationTimestamp
    private Timestamp createdTime;
    private String updatedBy;
    @UpdateTimestamp
    private Timestamp updatedTime;

    protected SingleBase() {
    }

    public SingleBase(String createdBy, Timestamp createdTime, String updatedBy, Timestamp updatedTime) {
        super();
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleBase that = (SingleBase) o;

        if (getCreatedBy() != null ? !getCreatedBy().equals(that.getCreatedBy()) : that.getCreatedBy() != null)
            return false;
        if (getCreatedTime() != null ? !getCreatedTime().equals(that.getCreatedTime()) : that.getCreatedTime() != null)
            return false;
        if (getUpdatedBy() != null ? !getUpdatedBy().equals(that.getUpdatedBy()) : that.getUpdatedBy() != null)
            return false;
        return getUpdatedTime() != null ? getUpdatedTime().equals(that.getUpdatedTime()) : that.getUpdatedTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getCreatedBy() != null ? getCreatedBy().hashCode() : 0;
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        result = 31 * result + (getUpdatedBy() != null ? getUpdatedBy().hashCode() : 0);
        result = 31 * result + (getUpdatedTime() != null ? getUpdatedTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SingleBase{" +
                "createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}' + super.toString();
    }
}
