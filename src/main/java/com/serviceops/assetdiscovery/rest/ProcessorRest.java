package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Processor} entity
 */
public class ProcessorRest extends AssetBaseRest implements Serializable {
    private   String processorName;
    private   String description;
    private   int width;
    private   float cpuSpeed;
    private   int coreCount;
    private   float externalClock;
    private   long l1CacheSize;
    private   long l2CacheSize;
    private   long l3CacheSize;
    private   String family;
    private   String deviceId;
    private   String socketDesignatio;


    public String getProcessorName() {
        return processorName;
    }

    public String getDescription() {
        return description;
    }

    public int getWidth() {
        return width;
    }

    public float getCpuSpeed() {
        return cpuSpeed;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public float getExternalClock() {
        return externalClock;
    }

    public long getL1CacheSize() {
        return l1CacheSize;
    }

    public long getL2CacheSize() {
        return l2CacheSize;
    }

    public long getL3CacheSize() {
        return l3CacheSize;
    }

    public String getFamily() {
        return family;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getSocketDesignatio() {
        return socketDesignatio;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setCpuSpeed(float cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public void setExternalClock(float externalClock) {
        this.externalClock = externalClock;
    }

    public void setL1CacheSize(long l1CacheSize) {
        this.l1CacheSize = l1CacheSize;
    }

    public void setL2CacheSize(long l2CacheSize) {
        this.l2CacheSize = l2CacheSize;
    }

    public void setL3CacheSize(long l3CacheSize) {
        this.l3CacheSize = l3CacheSize;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setSocketDesignatio(String socketDesignatio) {
        this.socketDesignatio = socketDesignatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessorRest entity = (ProcessorRest) o;
        return Objects.equals(this.processorName, entity.processorName) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.width, entity.width) &&
                Objects.equals(this.cpuSpeed, entity.cpuSpeed) &&
                Objects.equals(this.coreCount, entity.coreCount) &&
                Objects.equals(this.externalClock, entity.externalClock) &&
                Objects.equals(this.l1CacheSize, entity.l1CacheSize) &&
                Objects.equals(this.l2CacheSize, entity.l2CacheSize) &&
                Objects.equals(this.l3CacheSize, entity.l3CacheSize) &&
                Objects.equals(this.family, entity.family) &&
                Objects.equals(this.deviceId, entity.deviceId) &&
                Objects.equals(this.socketDesignatio, entity.socketDesignatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processorName, description, width, cpuSpeed, coreCount, externalClock, l1CacheSize, l2CacheSize, l3CacheSize, family, deviceId, socketDesignatio);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "processorName = " + processorName + ", " +
                "description = " + description + ", " +
                "width = " + width + ", " +
                "cpuSpeed = " + cpuSpeed + ", " +
                "coreCount = " + coreCount + ", " +
                "externalClock = " + externalClock + ", " +
                "l1CacheSize = " + l1CacheSize + ", " +
                "l2CacheSize = " + l2CacheSize + ", " +
                "l3CacheSize = " + l3CacheSize + ", " +
                "family = " + family + ", " +
                "deviceId = " + deviceId + ", " +
                "socketDesignatio = " + socketDesignatio + ")";
    }
}