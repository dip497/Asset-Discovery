package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class Ram extends AssetBase {

    private Long size;
    private String memoryType;
    private int width;
    private float clockSpeed;
    private String bankLocater;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(float clockSpeed) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ram ram = (Ram) o;

        if (getWidth() != ram.getWidth()) return false;
        if (Float.compare(ram.getClockSpeed(), getClockSpeed()) != 0) return false;
        if (getSize() != null ? !getSize().equals(ram.getSize()) : ram.getSize() != null) return false;
        if (getMemoryType() != null ? !getMemoryType().equals(ram.getMemoryType()) : ram.getMemoryType() != null)
            return false;
        return getBankLocater() != null ? getBankLocater().equals(ram.getBankLocater()) : ram.getBankLocater() == null;
    }

    @Override
    public int hashCode() {
        int result = getSize() != null ? getSize().hashCode() : 0;
        result = 31 * result + (getMemoryType() != null ? getMemoryType().hashCode() : 0);
        result = 31 * result + getWidth();
        result = 31 * result + (getClockSpeed() != +0.0f ? Float.floatToIntBits(getClockSpeed()) : 0);
        result = 31 * result + (getBankLocater() != null ? getBankLocater().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "size=" + size +
                ", memoryType='" + memoryType + '\'' +
                ", width=" + width +
                ", clockSpeed=" + clockSpeed +
                ", bankLocater='" + bankLocater + '\'' +
                '}';
    }
}
