package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

import java.sql.Date;
@Entity
public class Monitor extends SinglePnpDevice {

    private String monitorType;
    private float size;

    private Date installedDate;
    private int screenHeight;
    private int screenWidth;
    private int weekOfManufacture;
    private int yearOfManufacture;


}
