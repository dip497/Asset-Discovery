package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.mapped.SingleAssetBase;
import jakarta.persistence.Entity;

import java.net.InetAddress;
@Entity
public class Asset extends SingleAssetBase {
    private String hostName;
    private InetAddress ipAddress;
    private String usedBy;
    private String brand;
    private String itemType;

}
