package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.enums.Architecture;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

public class ComputerPropertiesRest extends AssetBaseRest implements Serializable {
    private  String osName;
    private  String osVersion;
    private  String osManufacturer;
    private Architecture architecture;
    private  String bootUpState;
    private  long memorySize;
    private  long diskSize;
    private  long cpuSpeed;
    private  long cpuCoreCount;
    private  long numberOfLogicalProcessors;
    private  String numberOfProcessors;
    private  String lastLoggedInUser;


    public void setOsName(String osName) {
        this.osName = osName;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setOsManufacturer(String osManufacturer) {
        this.osManufacturer = osManufacturer;
    }

    public void setOsArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    public void setBootUpState(String bootUpState) {
        this.bootUpState = bootUpState;
    }

    public void setMemorySize(long memorySize) {
        this.memorySize = memorySize;
    }

    public void setDiskSize(long diskSize) {
        this.diskSize = diskSize;
    }

    public void setCpuSpeed(long cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public void setCpuCoreCount(long cpuCoreCount) {
        this.cpuCoreCount = cpuCoreCount;
    }

    public void setNumberOfLogicalProcessors(long numberOfLogicalProcessors) {
        this.numberOfLogicalProcessors = numberOfLogicalProcessors;
    }

    public void setNumberOfProcessors(String numberOfProcessors) {
        this.numberOfProcessors = numberOfProcessors;
    }

    public void setLastLoggedInUser(String lastLoggedInUser) {
        this.lastLoggedInUser = lastLoggedInUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerPropertiesRest entity = (ComputerPropertiesRest) o;
        return Objects.equals(this.osName, entity.osName) &&
                Objects.equals(this.osVersion, entity.osVersion) &&
                Objects.equals(this.osManufacturer, entity.osManufacturer) &&
                Objects.equals(this.architecture, entity.architecture) &&
                Objects.equals(this.bootUpState, entity.bootUpState) &&
                Objects.equals(this.memorySize, entity.memorySize) &&
                Objects.equals(this.diskSize, entity.diskSize) &&
                Objects.equals(this.cpuSpeed, entity.cpuSpeed) &&
                Objects.equals(this.cpuCoreCount, entity.cpuCoreCount) &&
                Objects.equals(this.numberOfLogicalProcessors, entity.numberOfLogicalProcessors) &&
                Objects.equals(this.numberOfProcessors, entity.numberOfProcessors) &&
                Objects.equals(this.lastLoggedInUser, entity.lastLoggedInUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(osName, osVersion, osManufacturer, architecture, bootUpState, memorySize, diskSize, cpuSpeed, cpuCoreCount,numberOfLogicalProcessors, numberOfProcessors, lastLoggedInUser);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "osName = " + osName + ", " +
                "osVersion = " + osVersion + ", " +
                "osManufacturer = " + osManufacturer + ", " +
                "osArchitecture = " + architecture + ", " +
                "bootUpState = " + bootUpState + ", " +
                "memorySize = " + memorySize + ", " +
                "diskSize = " + diskSize + ", " +
                "cpuSpeed = " + cpuSpeed + ", " +
                "cpuCoreCount = " + cpuCoreCount + ", " +
                "numberOfLogicalProcessors = " + numberOfLogicalProcessors + ", " +
                "numberOfProcessors = " + numberOfProcessors + ", " +
                "lastLoggedInUser = " + lastLoggedInUser + ", " ;
    }
}