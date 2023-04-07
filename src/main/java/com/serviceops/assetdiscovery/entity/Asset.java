package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;
import org.aspectj.lang.annotation.Aspect;

import java.net.InetAddress;
@Entity
public class Asset extends SingleAssetBase {
    private String hostName;
    private InetAddress ipAddress;
    private String usedBy;
    private String brand;
    private String itemType;

}
