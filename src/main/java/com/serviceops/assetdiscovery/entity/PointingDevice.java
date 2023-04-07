package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

@Entity
public class PointingDevice extends SinglePnpDevice{
    private int numberOfButtons;
    private String pointingType;


}
