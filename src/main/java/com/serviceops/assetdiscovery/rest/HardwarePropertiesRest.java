package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * A Rest for the entity
 */
public class HardwarePropertiesRest extends AssetBaseRest implements Serializable {
    private  String warrantyExpirationDate;
    private  Date auditDate;

    public String getWarrantyExpirationDate() {
        return warrantyExpirationDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setWarrantyExpirationDate(String warrantyExpirationDate) {
        this.warrantyExpirationDate = warrantyExpirationDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HardwarePropertiesRest entity = (HardwarePropertiesRest) o;
        return Objects.equals(this.warrantyExpirationDate, entity.warrantyExpirationDate) &&
                Objects.equals(this.auditDate, entity.auditDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warrantyExpirationDate, auditDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "warrentyExpirationDate = " + warrantyExpirationDate + ", " +
                "auditDate = " + auditDate + ")";
    }
}