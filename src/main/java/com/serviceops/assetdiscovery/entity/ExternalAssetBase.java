package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class ExternalAssetBase extends AssetBase{
    private String description;
}
