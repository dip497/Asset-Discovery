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
    private   String width;
    private long cpuSpeed;
    private long coreCount;
    private long l1CacheSize;
    private long l2CacheSize;
    private long l3CacheSize;
    private   long family;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProcessorRest that = (ProcessorRest) o;
        return cpuSpeed == that.cpuSpeed && coreCount == that.coreCount && l1CacheSize == that.l1CacheSize && l2CacheSize == that.l2CacheSize && l3CacheSize == that.l3CacheSize && family == that.family && Objects.equals(processorName, that.processorName) && Objects.equals(description, that.description) && Objects.equals(width, that.width);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), processorName, description, width, cpuSpeed, coreCount, l1CacheSize, l2CacheSize, l3CacheSize, family);
    }

    @Override
    public String toString() {
        return "ProcessorRest{" +
                "processorName='" + processorName + '\'' +
                ", description='" + description + '\'' +
                ", width='" + width + '\'' +
                ", cpuSpeed=" + cpuSpeed +
                ", coreCount=" + coreCount +
                ", l1CacheSize=" + l1CacheSize +
                ", l2CacheSize=" + l2CacheSize +
                ", l3CacheSize=" + l3CacheSize +
                ", family=" + family +
                '}';
    }
}