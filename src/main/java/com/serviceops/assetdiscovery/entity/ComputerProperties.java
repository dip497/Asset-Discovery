package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class ComputerProperties extends AssetBase {
    private String osName;
    private String osVersion;
    private String servicePackName;
    private String osLicenseKey;
    private String osManufacturer;
    private String osArchitecture;
    private String bootUpState;
    private long memorySize;
    private long diskSize;
    private long cpuSpeed;
    private int cpuCoreCount;
    private boolean partOfDomain;
    private String domainName;
    private int numberOfLogicalProcessors;
    private int numberOfProcessors;
    private String pcSystemType;
    private String lastLoggedInUser;
    private String activationStatus;

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

    public String getServicePackName() {
        return servicePackName;
    }

    public void setServicePackName(String servicePackName) {
        this.servicePackName = servicePackName;
    }

    public String getOsLicenseKey() {
        return osLicenseKey;
    }

    public void setOsLicenseKey(String osLicenseKey) {
        this.osLicenseKey = osLicenseKey;
    }

    public String getOsManufacturer() {
        return osManufacturer;
    }

    public void setOsManufacturer(String osManufacturer) {
        this.osManufacturer = osManufacturer;
    }

    public String getOsArchitecture() {
        return osArchitecture;
    }

    public void setOsArchitecture(String osArchitecture) {
        this.osArchitecture = osArchitecture;
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

    public int getCpuCoreCount() {
        return cpuCoreCount;
    }

    public void setCpuCoreCount(int cpuCoreCount) {
        this.cpuCoreCount = cpuCoreCount;
    }

    public boolean isPartOfDomain() {
        return partOfDomain;
    }

    public void setPartOfDomain(boolean partOfDomain) {
        this.partOfDomain = partOfDomain;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public int getNumberOfLogicalProcessors() {
        return numberOfLogicalProcessors;
    }

    public void setNumberOfLogicalProcessors(int numberOfLogicalProcessors) {
        this.numberOfLogicalProcessors = numberOfLogicalProcessors;
    }

    public int getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public void setNumberOfProcessors(int numberOfProcessors) {
        this.numberOfProcessors = numberOfProcessors;
    }

    public String getPcSystemType() {
        return pcSystemType;
    }

    public void setPcSystemType(String pcSystemType) {
        this.pcSystemType = pcSystemType;
    }

    public String getLastLoggedInUser() {
        return lastLoggedInUser;
    }

    public void setLastLoggedInUser(String lastLoggedInUser) {
        this.lastLoggedInUser = lastLoggedInUser;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerProperties that = (ComputerProperties) o;

        if (getMemorySize() != that.getMemorySize()) return false;
        if (getDiskSize() != that.getDiskSize()) return false;
        if (getCpuSpeed() != that.getCpuSpeed()) return false;
        if (getCpuCoreCount() != that.getCpuCoreCount()) return false;
        if (isPartOfDomain() != that.isPartOfDomain()) return false;
        if (getNumberOfLogicalProcessors() != that.getNumberOfLogicalProcessors()) return false;
        if (getNumberOfProcessors() != that.getNumberOfProcessors()) return false;
        if (getOsName() != null ? !getOsName().equals(that.getOsName()) : that.getOsName() != null) return false;
        if (getOsVersion() != null ? !getOsVersion().equals(that.getOsVersion()) : that.getOsVersion() != null)
            return false;
        if (getServicePackName() != null ? !getServicePackName().equals(that.getServicePackName()) : that.getServicePackName() != null)
            return false;
        if (getOsLicenseKey() != null ? !getOsLicenseKey().equals(that.getOsLicenseKey()) : that.getOsLicenseKey() != null)
            return false;
        if (getOsManufacturer() != null ? !getOsManufacturer().equals(that.getOsManufacturer()) : that.getOsManufacturer() != null)
            return false;
        if (getOsArchitecture() != null ? !getOsArchitecture().equals(that.getOsArchitecture()) : that.getOsArchitecture() != null)
            return false;
        if (getBootUpState() != null ? !getBootUpState().equals(that.getBootUpState()) : that.getBootUpState() != null)
            return false;
        if (getDomainName() != null ? !getDomainName().equals(that.getDomainName()) : that.getDomainName() != null)
            return false;
        if (getPcSystemType() != null ? !getPcSystemType().equals(that.getPcSystemType()) : that.getPcSystemType() != null)
            return false;
        if (getLastLoggedInUser() != null ? !getLastLoggedInUser().equals(that.getLastLoggedInUser()) : that.getLastLoggedInUser() != null)
            return false;
        return getActivationStatus() != null ? getActivationStatus().equals(that.getActivationStatus()) : that.getActivationStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getOsName() != null ? getOsName().hashCode() : 0;
        result = 31 * result + (getOsVersion() != null ? getOsVersion().hashCode() : 0);
        result = 31 * result + (getServicePackName() != null ? getServicePackName().hashCode() : 0);
        result = 31 * result + (getOsLicenseKey() != null ? getOsLicenseKey().hashCode() : 0);
        result = 31 * result + (getOsManufacturer() != null ? getOsManufacturer().hashCode() : 0);
        result = 31 * result + (getOsArchitecture() != null ? getOsArchitecture().hashCode() : 0);
        result = 31 * result + (getBootUpState() != null ? getBootUpState().hashCode() : 0);
        result = 31 * result + (int) (getMemorySize() ^ (getMemorySize() >>> 32));
        result = 31 * result + (int) (getDiskSize() ^ (getDiskSize() >>> 32));
        result = 31 * result + (int) (getCpuSpeed() ^ (getCpuSpeed() >>> 32));
        result = 31 * result + getCpuCoreCount();
        result = 31 * result + (isPartOfDomain() ? 1 : 0);
        result = 31 * result + (getDomainName() != null ? getDomainName().hashCode() : 0);
        result = 31 * result + getNumberOfLogicalProcessors();
        result = 31 * result + getNumberOfProcessors();
        result = 31 * result + (getPcSystemType() != null ? getPcSystemType().hashCode() : 0);
        result = 31 * result + (getLastLoggedInUser() != null ? getLastLoggedInUser().hashCode() : 0);
        result = 31 * result + (getActivationStatus() != null ? getActivationStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComputerProperties{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", servicePackName='" + servicePackName + '\'' +
                ", osLicenseKey='" + osLicenseKey + '\'' +
                ", osManufacturer='" + osManufacturer + '\'' +
                ", osArchitecture='" + osArchitecture + '\'' +
                ", bootUpState='" + bootUpState + '\'' +
                ", memorySize=" + memorySize +
                ", diskSize=" + diskSize +
                ", cpuSpeed=" + cpuSpeed +
                ", cpuCoreCount=" + cpuCoreCount +
                ", partOfDomain=" + partOfDomain +
                ", domainName='" + domainName + '\'' +
                ", numberOfLogicalProcessors=" + numberOfLogicalProcessors +
                ", numberOfProcessors=" + numberOfProcessors +
                ", pcSystemType='" + pcSystemType + '\'' +
                ", lastLoggedInUser='" + lastLoggedInUser + '\'' +
                ", activationStatus='" + activationStatus + '\'' +
                '}';
    }
}
