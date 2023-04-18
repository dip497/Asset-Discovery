package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.enums.OsArchitecture;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

public class ComputerPropertiesRest extends AssetBaseRest implements Serializable {
    private  String osName;
    private  String osVersion;
    private  String osLicenseKey;
    private  String osManufacturer;
    private OsArchitecture osArchitecture;
    private  String bootUpState;
    private  String memorySize;
    private  long diskSize;
    private  long cpuSpeed;
    private  long cpuCoreCount;
    private  String partOfDomain;
    private  String domainName;
    private  long numberOfLogicalProcessors;
    private  String numberOfProcessors;
    private  String pcSystemType;
    private  String lastLoggedInUser;
    private  String activationStatus;

    public String getOsName() {
        return osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getOsLicenseKey() {
        return osLicenseKey;
    }

    public String getOsManufacturer() {
        return osManufacturer;
    }

    public OsArchitecture getOsArchitecture() {
        return osArchitecture;
    }

    public String getBootUpState() {
        return bootUpState;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public long getDiskSize() {
        return diskSize;
    }

    public long getCpuSpeed() {
        return cpuSpeed;
    }

    public long getCpuCoreCount() {
        return cpuCoreCount;
    }

    public String getPartOfDomain() {
        return partOfDomain;
    }

    public String getDomainName() {
        return domainName;
    }

    public long getNumberOfLogicalProcessors() {
        return numberOfLogicalProcessors;
    }

    public String getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public String getPcSystemType() {
        return pcSystemType;
    }

    public String getLastLoggedInUser() {
        return lastLoggedInUser;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setOsLicenseKey(String osLicenseKey) {
        this.osLicenseKey = osLicenseKey;
    }

    public void setOsManufacturer(String osManufacturer) {
        this.osManufacturer = osManufacturer;
    }

    public void setOsArchitecture(OsArchitecture osArchitecture) {
        this.osArchitecture = osArchitecture;
    }

    public void setBootUpState(String bootUpState) {
        this.bootUpState = bootUpState;
    }

    public void setMemorySize(String memorySize) {
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

    public void setPartOfDomain(String partOfDomain) {
        this.partOfDomain = partOfDomain;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setNumberOfLogicalProcessors(long numberOfLogicalProcessors) {
        this.numberOfLogicalProcessors = numberOfLogicalProcessors;
    }

    public void setNumberOfProcessors(String numberOfProcessors) {
        this.numberOfProcessors = numberOfProcessors;
    }

    public void setPcSystemType(String pcSystemType) {
        this.pcSystemType = pcSystemType;
    }

    public void setLastLoggedInUser(String lastLoggedInUser) {
        this.lastLoggedInUser = lastLoggedInUser;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerPropertiesRest entity = (ComputerPropertiesRest) o;
        return Objects.equals(this.osName, entity.osName) &&
                Objects.equals(this.osVersion, entity.osVersion) &&
                Objects.equals(this.osLicenseKey, entity.osLicenseKey) &&
                Objects.equals(this.osManufacturer, entity.osManufacturer) &&
                Objects.equals(this.osArchitecture, entity.osArchitecture) &&
                Objects.equals(this.bootUpState, entity.bootUpState) &&
                Objects.equals(this.memorySize, entity.memorySize) &&
                Objects.equals(this.diskSize, entity.diskSize) &&
                Objects.equals(this.cpuSpeed, entity.cpuSpeed) &&
                Objects.equals(this.cpuCoreCount, entity.cpuCoreCount) &&
                Objects.equals(this.partOfDomain, entity.partOfDomain) &&
                Objects.equals(this.domainName, entity.domainName) &&
                Objects.equals(this.numberOfLogicalProcessors, entity.numberOfLogicalProcessors) &&
                Objects.equals(this.numberOfProcessors, entity.numberOfProcessors) &&
                Objects.equals(this.pcSystemType, entity.pcSystemType) &&
                Objects.equals(this.lastLoggedInUser, entity.lastLoggedInUser) &&
                Objects.equals(this.activationStatus, entity.activationStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(osName, osVersion, osLicenseKey, osManufacturer, osArchitecture, bootUpState, memorySize, diskSize, cpuSpeed, cpuCoreCount, partOfDomain, domainName, numberOfLogicalProcessors, numberOfProcessors, pcSystemType, lastLoggedInUser, activationStatus);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "osName = " + osName + ", " +
                "osVersion = " + osVersion + ", " +
                "osLicenseKey = " + osLicenseKey + ", " +
                "osManufacturer = " + osManufacturer + ", " +
                "osArchitecture = " + osArchitecture + ", " +
                "bootUpState = " + bootUpState + ", " +
                "memorySize = " + memorySize + ", " +
                "diskSize = " + diskSize + ", " +
                "cpuSpeed = " + cpuSpeed + ", " +
                "cpuCoreCount = " + cpuCoreCount + ", " +
                "partOfDomain = " + partOfDomain + ", " +
                "domainName = " + domainName + ", " +
                "numberOfLogicalProcessors = " + numberOfLogicalProcessors + ", " +
                "numberOfProcessors = " + numberOfProcessors + ", " +
                "pcSystemType = " + pcSystemType + ", " +
                "lastLoggedInUser = " + lastLoggedInUser + ", " +
                "activationStatus = " + activationStatus + ")";
    }
}