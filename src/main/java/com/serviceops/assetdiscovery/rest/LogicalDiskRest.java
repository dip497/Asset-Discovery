package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.LogicalDisk} entity
 */
public class LogicalDiskRest extends AssetBaseRest implements Serializable {
    private  String name;
    private  String description;
    private  String fileSystemType;
    private  String driveType;
    private  long size;
    private  long freeSpace;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFileSystemType() {
        return fileSystemType;
    }

    public String getDriveType() {
        return driveType;
    }

    public long getSize() {
        return size;
    }

    public long getFreeSpace() {
        return freeSpace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFileSystemType(String fileSystemType) {
        this.fileSystemType = fileSystemType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setFreeSpace(long freeSpace) {
        this.freeSpace = freeSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicalDiskRest entity = (LogicalDiskRest) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.fileSystemType, entity.fileSystemType) &&
                Objects.equals(this.driveType, entity.driveType) &&
                Objects.equals(this.size, entity.size) &&
                Objects.equals(this.freeSpace, entity.freeSpace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, fileSystemType, driveType, size, freeSpace);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "fileSystemType = " + fileSystemType + ", " +
                "driveType = " + driveType + ", " +
                "size = " + size + ", " +
                "freeSpace = " + freeSpace + ")";
    }
}