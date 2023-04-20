package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.enums.Architecture;
import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;

import java.io.Serializable;

public class ComputerPropertiesRest extends SingleBaseRest implements Serializable {
    private long refId;
    private String osName;
    private String osVersion;
    private String osManufacturer;
    private Architecture architecture;
    private String bootUpState;
    private long memorySize;
    private long diskSize;
    private long cpuSpeed;
    private long cpuCoreCount;
    private long numberOfLogicalProcessors;
    private long numberOfProcessors;
    private String lastLoggedInUser;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsManufacturer() {
        return osManufacturer;
    }

    public void setOsManufacturer(String osManufacturer) {
        this.osManufacturer = osManufacturer;
    }

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    public String getBootUpState() {
        return bootUpState;
    }

    public void setBootUpState(String bootUpState) {
        this.bootUpState = bootUpState;
    }

    public long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(long memorySize) {
        this.memorySize = memorySize;
    }

    public long getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(long diskSize) {
        this.diskSize = diskSize;
    }

    public long getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(long cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public long getCpuCoreCount() {
        return cpuCoreCount;
    }

    public void setCpuCoreCount(long cpuCoreCount) {
        this.cpuCoreCount = cpuCoreCount;
    }

    public long getNumberOfLogicalProcessors() {
        return numberOfLogicalProcessors;
    }

    public void setNumberOfLogicalProcessors(long numberOfLogicalProcessors) {
        this.numberOfLogicalProcessors = numberOfLogicalProcessors;
    }

    public long getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public void setNumberOfProcessors(long numberOfProcessors) {
        this.numberOfProcessors = numberOfProcessors;
    }

    public String getLastLoggedInUser() {
        return lastLoggedInUser;
    }

    public void setLastLoggedInUser(String lastLoggedInUser) {
        this.lastLoggedInUser = lastLoggedInUser;
    }
}