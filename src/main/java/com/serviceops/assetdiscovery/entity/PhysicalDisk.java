package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class PhysicalDisk extends SinglePnpDevice{

    private String name;
    private long size;
    private Date installedDate;

    private int partition;
    private String mediaType;
    private String model;
    private String interfaceType;

}
