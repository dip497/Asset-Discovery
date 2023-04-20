package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import jakarta.persistence.Entity;

@Entity
public class LogicalDisk extends SingleBase {

    private long refId;
    private String name;
    private String description;
    private String fileSystemType;
    private long size;
    private String serialNumber;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
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

    public String getFileSystemType() {
        return fileSystemType;
    }

    public void setFileSystemType(String fileSystemType) {
        this.fileSystemType = fileSystemType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "LogicalDisk{" + "refId=" + refId + ", name='" + name + '\'' + ", description='" + description
                + '\'' + ", fileSystemType='" + fileSystemType + '\'' + ", size=" + size + ", serialNumber='"
                + serialNumber + '\'' + '}';
    }
}
