package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.PnpDevicesBase;
import jakarta.persistence.Entity;

@Entity
public class PointingDevice extends PnpDevicesBase {
    private int numberOfButtons;
    private String pointingType;


}
