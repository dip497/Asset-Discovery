package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class HardwareProperties extends SingleAssetBase{
    private Date warrentyExpirationDate;
    private Date auditDate;
}
