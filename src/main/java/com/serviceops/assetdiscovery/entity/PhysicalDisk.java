package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class PhysicalDisk extends AssetBase {
    private long refId;
    private String manufacturer;
    private String name;
    private String description;
    private long size;
    private int partition;
    private String mediaType;
    private String model;
    private String interfaceType;

    @Override
    public long getRefId() {
        return refId;
    }

    @Override
    public void setRefId(long refId) {
        this.refId = refId;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

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
}
