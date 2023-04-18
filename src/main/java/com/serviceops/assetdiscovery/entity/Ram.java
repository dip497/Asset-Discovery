package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class Ram extends AssetBase {

    private Long size;
    private String memoryType;
    private Long width;
    private Long clockSpeed;
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

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(Long clockSpeed) {
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
        if (!super.equals(o)) return false;

        Ram ram = (Ram) o;

        if (getSize() != null ? !getSize().equals(ram.getSize()) : ram.getSize() != null) return false;
        if (getMemoryType() != null ? !getMemoryType().equals(ram.getMemoryType()) : ram.getMemoryType() != null)
            return false;
        if (getWidth() != null ? !getWidth().equals(ram.getWidth()) : ram.getWidth() != null) return false;
        if (getClockSpeed() != null ? !getClockSpeed().equals(ram.getClockSpeed()) : ram.getClockSpeed() != null)
            return false;
        return getBankLocater() != null ? getBankLocater().equals(ram.getBankLocater()) : ram.getBankLocater() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getMemoryType() != null ? getMemoryType().hashCode() : 0);
        result = 31 * result + (getWidth() != null ? getWidth().hashCode() : 0);
        result = 31 * result + (getClockSpeed() != null ? getClockSpeed().hashCode() : 0);
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
                "} " + super.toString();
    }
}
