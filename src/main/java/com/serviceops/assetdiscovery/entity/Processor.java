package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Processor extends AssetBase {
    private String processorName;
    private String description;
    private String width;
    private long cpuSpeed;
    private long coreCount;
    private long l1CacheSize;
    private long l2CacheSize;
    private long l3CacheSize;
    private long family;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Processor processor = (Processor) o;
        return cpuSpeed == processor.cpuSpeed && coreCount == processor.coreCount && l1CacheSize == processor.l1CacheSize && l2CacheSize == processor.l2CacheSize && l3CacheSize == processor.l3CacheSize && Objects.equals(processorName, processor.processorName) && Objects.equals(description, processor.description) && Objects.equals(width, processor.width) && Objects.equals(family, processor.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), processorName, description, width, cpuSpeed, coreCount, l1CacheSize, l2CacheSize, l3CacheSize, family);
    }

    @Override
    public String toString() {
        return "Processor{" +
                "processorName='" + processorName + '\'' +
                ", description='" + description + '\'' +
                ", width='" + width + '\'' +
                ", cpuSpeed=" + cpuSpeed +
                ", coreCount=" + coreCount +
                ", l1CacheSize=" + l1CacheSize +
                ", l2CacheSize=" + l2CacheSize +
                ", l3CacheSize=" + l3CacheSize +
                ", family='" + family + '\'' +
                '}';
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