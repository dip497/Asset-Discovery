package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class SingleAssetBase extends Base{
    private Long refId;
    private String serialNumber;
}
