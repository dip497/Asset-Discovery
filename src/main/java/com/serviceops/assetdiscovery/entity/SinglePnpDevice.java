package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class SinglePnpDevice extends ExternalAssetBase {
    private String pnpDeviceId;
}
