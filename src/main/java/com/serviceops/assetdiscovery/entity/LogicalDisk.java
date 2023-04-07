package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.ExternalAssetBase;
import jakarta.persistence.Entity;

@Entity
public class LogicalDisk extends ExternalAssetBase {
    private String name;
    private String fileSystemType;
    private String driveType;

    private long size;
    private long freeSpace;

}
