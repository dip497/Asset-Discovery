package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.HardwareProperties} entity
 */
public class HardwarePropertiesRest extends AssetBaseRest implements Serializable {
    private  Date warrentyExpirationDate;
    private  Date auditDate;

    public Date getWarrentyExpirationDate() {
        return warrentyExpirationDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setWarrentyExpirationDate(Date warrentyExpirationDate) {
        this.warrentyExpirationDate = warrentyExpirationDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HardwarePropertiesRest entity = (HardwarePropertiesRest) o;
        return Objects.equals(this.warrentyExpirationDate, entity.warrentyExpirationDate) &&
                Objects.equals(this.auditDate, entity.auditDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warrentyExpirationDate, auditDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "warrentyExpirationDate = " + warrentyExpirationDate + ", " +
                "auditDate = " + auditDate + ")";
    }
}