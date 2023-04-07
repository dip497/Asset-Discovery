package com.serviceops.assetdiscovery.entity.mapped;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class PnpDevicesBase extends ExternalAssetBase {
    private String pnpDeviceId;
}
