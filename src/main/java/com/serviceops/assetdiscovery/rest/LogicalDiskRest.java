package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.LogicalDisk} entity
 */
public class LogicalDiskRest extends AssetBaseRest implements Serializable {
    private  String name;
    private  String description;
    private  String fileSystemType;
    private  Long size;

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

    public String getFileSystemType() {
        return fileSystemType;
    }

    public void setFileSystemType(String fileSystemType) {
        this.fileSystemType = fileSystemType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LogicalDiskRest that = (LogicalDiskRest) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(fileSystemType, that.fileSystemType) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, fileSystemType, size);
    }

    @Override
    public String toString() {
        return "LogicalDiskRest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fileSystemType='" + fileSystemType + '\'' +
                ", size=" + size +
                '}';
    }
}