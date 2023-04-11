package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Processor extends AssetBase {
    private String processorName;
    private String description;
    private String width;
    private String cpuSpeed;
    private int coreCount;
    private String externalClock;
    private String l1CacheSize;
    private String l2CacheSize;
    private String l3CacheSize;
    private String family;
    private String deviceId;
    private String socketDesignation;

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

    public String getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(String cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public String getExternalClock() {
        return externalClock;
    }

    public void setExternalClock(String externalClock) {
        this.externalClock = externalClock;
    }

    public String getL1CacheSize() {
        return l1CacheSize;
    }

    public void setL1CacheSize(String l1CacheSize) {
        this.l1CacheSize = l1CacheSize;
    }

    public String getL2CacheSize() {
        return l2CacheSize;
    }

    public void setL2CacheSize(String l2CacheSize) {
        this.l2CacheSize = l2CacheSize;
    }

    public String getL3CacheSize() {
        return l3CacheSize;
    }

    public void setL3CacheSize(String l3CacheSize) {
        this.l3CacheSize = l3CacheSize;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSocketDesignation() {
        return socketDesignation;
    }

    public void setSocketDesignation(String socketDesignation) {
        this.socketDesignation = socketDesignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Processor processor = (Processor) o;
        return coreCount == processor.coreCount && Objects.equals(processorName, processor.processorName) && Objects.equals(description, processor.description) && Objects.equals(width, processor.width) && Objects.equals(cpuSpeed, processor.cpuSpeed) && Objects.equals(externalClock, processor.externalClock) && Objects.equals(l1CacheSize, processor.l1CacheSize) && Objects.equals(l2CacheSize, processor.l2CacheSize) && Objects.equals(l3CacheSize, processor.l3CacheSize) && Objects.equals(family, processor.family) && Objects.equals(deviceId, processor.deviceId) && Objects.equals(socketDesignation, processor.socketDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), processorName, description, width, cpuSpeed, coreCount, externalClock, l1CacheSize, l2CacheSize, l3CacheSize, family, deviceId, socketDesignation);
    }

    @Override
    public String toString() {
        return "Processor{" +
                "processorName='" + processorName + '\'' +
                ", description='" + description + '\'' +
                ", width=" + width +
                ", cpuSpeed=" + cpuSpeed +
                ", coreCount=" + coreCount +
                ", externalClock=" + externalClock +
                ", l1CacheSize=" + l1CacheSize +
                ", l2CacheSize=" + l2CacheSize +
                ", l3CacheSize=" + l3CacheSize +
                ", family='" + family + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", socketDesignatio='" + socketDesignation + '\'' +
                '}';
    }
}
