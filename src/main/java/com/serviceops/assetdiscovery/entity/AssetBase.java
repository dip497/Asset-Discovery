package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AssetBase extends SingleAssetBase{
    private String manufacturer;
    private String deviceStatus;

}
