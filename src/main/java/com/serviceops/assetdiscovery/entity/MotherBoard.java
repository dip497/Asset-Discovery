package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.AssetBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class MotherBoard extends AssetBase {
    private String version;
    private Date installedDate;
    private String partNumber;
    private String primaryBusType;
    private String secondaryBusType;


}
