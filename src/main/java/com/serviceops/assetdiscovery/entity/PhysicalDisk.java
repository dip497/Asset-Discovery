package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class PhysicalDisk extends AssetBase {

    private String name;
    private String description;
    private long size;
    private int partition;
    private String mediaType;
    private String model;
    private String interfaceType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PhysicalDisk that = (PhysicalDisk) o;
        return partition == that.partition && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(size, that.size) && Objects.equals(mediaType, that.mediaType) && Objects.equals(model, that.model) && Objects.equals(interfaceType, that.interfaceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, size, partition, mediaType, model, interfaceType);
    }

    @Override
    public String toString() {
        return "PhysicalDisk{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", partition=" + partition +
                ", mediaType='" + mediaType + '\'' +
                ", model='" + model + '\'' +
                ", interfaceType='" + interfaceType + '\'' +
                '}';
    }
}
