package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.PnpDevicesBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class PhysicalDisk extends PnpDevicesBase {

    private String name;
    private long size;
    private Date installedDate;

    private int partition;
    private String mediaType;
    private String model;
    private String interfaceType;

}
