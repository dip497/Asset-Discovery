package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.PnpDevicesBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class Keyboard extends PnpDevicesBase {
    private String name;
    private Date installedDate;


}
