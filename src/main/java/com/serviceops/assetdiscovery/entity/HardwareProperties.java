package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.SingleAssetBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class HardwareProperties extends SingleAssetBase {
    private Date warrentyExpirationDate;
    private Date auditDate;
}
