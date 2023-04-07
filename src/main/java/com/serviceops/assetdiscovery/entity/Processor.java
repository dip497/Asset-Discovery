package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.ExternalAssetBase;
import jakarta.persistence.Entity;

@Entity
public class Processor extends ExternalAssetBase {
    private String processorName;
    private int width;
    private float cpuSpeed;
    private int coreCount;
    private float externalClock;
    private long l1CacheSize;
    private long l2CacheSize;
    private long l3CacheSize;
    private String family;
    private String deviceId;
    private String socketDesignatio;

}
