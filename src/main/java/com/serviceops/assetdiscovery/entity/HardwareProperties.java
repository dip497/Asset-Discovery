package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.AssetBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class HardwareProperties extends AssetBase {
    private Date warrentyExpirationDate;
    private Date auditDate;

    public Date getWarrentyExpirationDate() {
        return warrentyExpirationDate;
    }

    public void setWarrentyExpirationDate(Date warrentyExpirationDate) {
        this.warrentyExpirationDate = warrentyExpirationDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HardwareProperties that = (HardwareProperties) o;

        if (getWarrentyExpirationDate() != null ? !getWarrentyExpirationDate().equals(that.getWarrentyExpirationDate()) : that.getWarrentyExpirationDate() != null)
            return false;
        return getAuditDate() != null ? getAuditDate().equals(that.getAuditDate()) : that.getAuditDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getWarrentyExpirationDate() != null ? getWarrentyExpirationDate().hashCode() : 0;
        result = 31 * result + (getAuditDate() != null ? getAuditDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HardwareProperties{" +
                "warrentyExpirationDate=" + warrentyExpirationDate +
                ", auditDate=" + auditDate +
                '}';
    }
}
