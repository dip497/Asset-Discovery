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
    private   long size;
    private   int partition;
    private   String mediaType;
    private   String model;
    private   String interfaceType;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PhysicalDiskRest that = (PhysicalDiskRest) o;
        return size == that.size && partition == that.partition && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(mediaType, that.mediaType) && Objects.equals(model, that.model) && Objects.equals(interfaceType, that.interfaceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, size, partition, mediaType, model, interfaceType);
    }

    @Override
    public String
    toString() {
        return "PhysicalDiskRest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", partition=" + partition +
                ", mediaType='" + mediaType + '\'' +
                ", model='" + model + '\'' +
                ", interfaceType='" + interfaceType + '\'' +
                '}';
    }
}