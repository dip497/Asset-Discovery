package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class Processor extends AssetBase {
    private String processorName;
    private String description;
    private int width;
    private float cpuSpeed;
    private int coreCount;
    private float externalClock;
    private long l1CacheSize;
    private long l2CacheSize;
    private long l3CacheSize;
    private String family;
    private String deviceId;
    private String socketDesignatio;

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(float cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public float getExternalClock() {
        return externalClock;
    }

    public void setExternalClock(float externalClock) {
        this.externalClock = externalClock;
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

    public String getSocketDesignatio() {
        return socketDesignatio;
    }

    public void setSocketDesignatio(String socketDesignatio) {
        this.socketDesignatio = socketDesignatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Processor processor = (Processor) o;

        if (getWidth() != processor.getWidth()) return false;
        if (Float.compare(processor.getCpuSpeed(), getCpuSpeed()) != 0) return false;
        if (getCoreCount() != processor.getCoreCount()) return false;
        if (Float.compare(processor.getExternalClock(), getExternalClock()) != 0) return false;
        if (getL1CacheSize() != processor.getL1CacheSize()) return false;
        if (getL2CacheSize() != processor.getL2CacheSize()) return false;
        if (getL3CacheSize() != processor.getL3CacheSize()) return false;
        if (getProcessorName() != null ? !getProcessorName().equals(processor.getProcessorName()) : processor.getProcessorName() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(processor.getDescription()) : processor.getDescription() != null)
            return false;
        if (getFamily() != null ? !getFamily().equals(processor.getFamily()) : processor.getFamily() != null)
            return false;
        if (getDeviceId() != null ? !getDeviceId().equals(processor.getDeviceId()) : processor.getDeviceId() != null)
            return false;
        return getSocketDesignatio() != null ? getSocketDesignatio().equals(processor.getSocketDesignatio()) : processor.getSocketDesignatio() == null;
    }

    @Override
    public int hashCode() {
        int result = getProcessorName() != null ? getProcessorName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getWidth();
        result = 31 * result + (getCpuSpeed() != +0.0f ? Float.floatToIntBits(getCpuSpeed()) : 0);
        result = 31 * result + getCoreCount();
        result = 31 * result + (getExternalClock() != +0.0f ? Float.floatToIntBits(getExternalClock()) : 0);
        result = 31 * result + (int) (getL1CacheSize() ^ (getL1CacheSize() >>> 32));
        result = 31 * result + (int) (getL2CacheSize() ^ (getL2CacheSize() >>> 32));
        result = 31 * result + (int) (getL3CacheSize() ^ (getL3CacheSize() >>> 32));
        result = 31 * result + (getFamily() != null ? getFamily().hashCode() : 0);
        result = 31 * result + (getDeviceId() != null ? getDeviceId().hashCode() : 0);
        result = 31 * result + (getSocketDesignatio() != null ? getSocketDesignatio().hashCode() : 0);
        return result;
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
                ", socketDesignatio='" + socketDesignatio + '\'' +
                '}';
    }
}
