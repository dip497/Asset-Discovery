package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import com.serviceops.assetdiscovery.utils.UnitConverter;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class LogicalDisk extends AssetBase {
    /*
            logicalDisk.setDescription(data[0]);
        logicalDisk.setName(data[1]);
        logicalDisk.setSerialNumber(data[2]);
        logicalDisk.setSize(UnitConverter.convertToBytes(data[3]));
        logicalDisk.setFileSystemType(data[4]);*/
    private String name;
    private String description;
    private String fileSystemType;
    private Long size;
//    private String driveType;//
//    private Long freeSpace;//


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
        LogicalDisk that = (LogicalDisk) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(fileSystemType, that.fileSystemType) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, fileSystemType, size);
    }

    @Override
    public String toString() {
        return "LogicalDisk{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fileSystemType='" + fileSystemType + '\'' +
                ", size=" + size +
                '}';
    }
}
