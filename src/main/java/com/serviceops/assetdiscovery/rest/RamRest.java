package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.Ram;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;

/**
 * A Rest for the {@link Ram} entity
 */
public class RamRest extends AssetBaseRest implements Serializable {
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
    public String toString() {
        return "RamRest{" + "size=" + size + ", memoryType='" + memoryType + '\'' + ", width=" + width
                + ", clockSpeed=" + clockSpeed + ", bankLocater='" + bankLocater + '\'' + "} "
                + super.toString();
    }
}