package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.Ram;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link Ram} entity
 */
public class RamRest extends AssetBaseRest implements Serializable {
    private  String size;
    private  String memoryType;
    private  String width;
    private  String clockSpeed;
    private  String bankLocater;

    public String getSize() {
        return size;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public String getWidth() {
        return width;
    }

    public String getClockSpeed() {
        return clockSpeed;
    }

    public String getBankLocater() {
        return bankLocater;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setClockSpeed(String clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public void setBankLocater(String bankLocater) {
        this.bankLocater = bankLocater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RamRest entity = (RamRest) o;
        return Objects.equals(this.size, entity.size) &&
                Objects.equals(this.memoryType, entity.memoryType) &&
                Objects.equals(this.width, entity.width) &&
                Objects.equals(this.clockSpeed, entity.clockSpeed) &&
                Objects.equals(this.bankLocater, entity.bankLocater);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, memoryType, width, clockSpeed, bankLocater);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "size = " + size + ", " +
                "memoryType = " + memoryType + ", " +
                "width = " + width + ", " +
                "clockSpeed = " + clockSpeed + ", " +
                "bankLocater = " + bankLocater + ")";
    }
}