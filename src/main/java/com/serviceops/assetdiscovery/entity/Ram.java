package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

@Entity
public class Ram extends AssetBase {

    private Long size;
    private String memoryType;
    private int width;
    private float clockSpeed;
    private String bankLocater;


}
