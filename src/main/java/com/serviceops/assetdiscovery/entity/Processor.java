package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AuditBase;
import jakarta.persistence.Entity;

@Entity
public class Processor extends AuditBase {
    private long refId;
    private String manufacturer;
    private String processorName;
    private String description;
    private String width;
    private long cpuSpeed;
    private long coreCount;
    private long l1CacheSize;
    private long l2CacheSize;
    private long l3CacheSize;
    private long family;


    public long getRefId() {
        return refId;
    }


    public void setRefId(long refId) {
        this.refId = refId;
    }


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public long getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(long cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public long getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(long coreCount) {
        this.coreCount = coreCount;
    }

    public long getL1CacheSize() {
        return l1CacheSize;
    }

    public void setL1CacheSize(long l1CacheSize) {
        this.l1CacheSize = l1CacheSize;
    }

    public long getL2CacheSize() {
        return l2CacheSize;
    }

    public void setL2CacheSize(long l2CacheSize) {
        this.l2CacheSize = l2CacheSize;
    }

    public long getL3CacheSize() {
        return l3CacheSize;
    }

    public void setL3CacheSize(long l3CacheSize) {
        this.l3CacheSize = l3CacheSize;
    }

    public long getFamily() {
        return family;
    }

    public void setFamily(long family) {
        this.family = family;
    }
}