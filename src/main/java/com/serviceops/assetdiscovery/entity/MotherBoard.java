package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class MotherBoard extends AssetBase{
    private String version;
    private Date installedDate;
    private String partNumber;
    private String primaryBusType;
    private String secondaryBusType;


}
