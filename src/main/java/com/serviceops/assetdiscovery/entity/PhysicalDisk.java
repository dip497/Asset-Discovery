package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;

import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class PhysicalDisk extends AssetBase {

    //   lshw -class disk

    private String name;  // product name
    private String description;  // description
    private long size;       // size
    private Date installedDate;
    private int partition;
    private String mediaType;
    private String model;
    private String interfaceType;
    private String pnpDeviceId;  // serial number

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public void setPnpDeviceId(String pnpDeviceId) {
        this.pnpDeviceId = pnpDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhysicalDisk that = (PhysicalDisk) o;

        if (getSize() != that.getSize()) return false;
        if (getPartition() != that.getPartition()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getInstalledDate() != null ? !getInstalledDate().equals(that.getInstalledDate()) : that.getInstalledDate() != null)
            return false;
        if (getMediaType() != null ? !getMediaType().equals(that.getMediaType()) : that.getMediaType() != null)
            return false;
        if (getModel() != null ? !getModel().equals(that.getModel()) : that.getModel() != null) return false;
        if (getInterfaceType() != null ? !getInterfaceType().equals(that.getInterfaceType()) : that.getInterfaceType() != null)
            return false;
        return getPnpDeviceId() != null ? getPnpDeviceId().equals(that.getPnpDeviceId()) : that.getPnpDeviceId() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (int) (getSize() ^ (getSize() >>> 32));
        result = 31 * result + (getInstalledDate() != null ? getInstalledDate().hashCode() : 0);
        result = 31 * result + getPartition();
        result = 31 * result + (getMediaType() != null ? getMediaType().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getInterfaceType() != null ? getInterfaceType().hashCode() : 0);
        result = 31 * result + (getPnpDeviceId() != null ? getPnpDeviceId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhysicalDisk{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", installedDate=" + installedDate +
                ", partition=" + partition +
                ", mediaType='" + mediaType + '\'' +
                ", model='" + model + '\'' +
                ", interfaceType='" + interfaceType + '\'' +
                ", pnpDeviceId='" + pnpDeviceId + '\'' +
                '}';
    }
}
