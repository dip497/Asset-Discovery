package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

@Entity
public class LogicalDisk extends ExternalAssetBase{
    private String name;
    private String fileSystemType;
    private String driveType;

    private long size;
    private long freeSpace;

}
