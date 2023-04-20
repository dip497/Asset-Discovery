package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.Ram;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;

/**
 * A Rest for the {@link Ram} entity
 */
public class RamRest extends AssetBaseRest implements Serializable {
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
    public String toString() {
        return "RamRest{" + "size=" + size + ", memoryType='" + memoryType + '\'' + ", width=" + width
                + ", clockSpeed=" + clockSpeed + ", bankLocater='" + bankLocater + '\'' + "} "
                + super.toString();
    }
}