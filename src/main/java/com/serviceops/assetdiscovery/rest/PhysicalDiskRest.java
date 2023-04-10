package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.PhysicalDisk} entity
 */
public class PhysicalDiskRest extends AssetBaseRest implements Serializable {
    private   String name;
    private   String description;
    private   String size;
    private   Date installedDate;
    private   int partition;
    private   String mediaType;
    private   String model;
    private   String interfaceType;
    private   String pnpDeviceId;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public int getPartition() {
        return partition;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getModel() {
        return model;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public void setPnpDeviceId(String pnpDeviceId) {
        this.pnpDeviceId = pnpDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhysicalDiskRest entity = (PhysicalDiskRest) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.size, entity.size) &&
                Objects.equals(this.installedDate, entity.installedDate) &&
                Objects.equals(this.partition, entity.partition) &&
                Objects.equals(this.mediaType, entity.mediaType) &&
                Objects.equals(this.model, entity.model) &&
                Objects.equals(this.interfaceType, entity.interfaceType) &&
                Objects.equals(this.pnpDeviceId, entity.pnpDeviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, size, installedDate, partition, mediaType, model, interfaceType, pnpDeviceId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "size = " + size + ", " +
                "installedDate = " + installedDate + ", " +
                "partition = " + partition + ", " +
                "mediaType = " + mediaType + ", " +
                "model = " + model + ", " +
                "interfaceType = " + interfaceType + ", " +
                "pnpDeviceId = " + pnpDeviceId + ")";
    }
}