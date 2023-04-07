package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.PnpDevicesBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class Monitor extends PnpDevicesBase {

    private String monitorType;
    private float size;

    private Date installedDate;
    private int screenHeight;
    private int screenWidth;
    private int weekOfManufacture;
    private int yearOfManufacture;


}
