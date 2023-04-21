package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AuditBase;
import jakarta.persistence.Entity;

@Entity
public class Ram extends AuditBase {
    private long refId;
    private String serialNumber;
    private String manufacturer;
    private long size;
    private String memoryType;
    private long width;
    private long clockSpeed;
    private String bankLocater;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(long clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public String getBankLocater() {
        return bankLocater;
    }

    public void setBankLocater(String bankLocater) {
        this.bankLocater = bankLocater;
    }
}
