package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class Ram extends AssetBase {

    private long size;
    private String memoryType;
    private long width;
    private long clockSpeed;
    private String bankLocater;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Ram ram = (Ram) o;

        if (getSize() != ram.getSize())
            return false;
        if (getWidth() != ram.getWidth())
            return false;
        if (getClockSpeed() != ram.getClockSpeed())
            return false;
        if (getMemoryType() != null ?
                !getMemoryType().equals(ram.getMemoryType()) :
                ram.getMemoryType() != null)
            return false;
        return getBankLocater() != null ?
                getBankLocater().equals(ram.getBankLocater()) :
                ram.getBankLocater() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (getSize() ^ (getSize() >>> 32));
        result = 31 * result + (getMemoryType() != null ? getMemoryType().hashCode() : 0);
        result = 31 * result + (int) (getWidth() ^ (getWidth() >>> 32));
        result = 31 * result + (int) (getClockSpeed() ^ (getClockSpeed() >>> 32));
        result = 31 * result + (getBankLocater() != null ? getBankLocater().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ram{" + "size=" + size + ", memoryType='" + memoryType + '\'' + ", width=" + width
                + ", clockSpeed=" + clockSpeed + ", bankLocater='" + bankLocater + '\'' + "} "
                + super.toString();
    }
}
