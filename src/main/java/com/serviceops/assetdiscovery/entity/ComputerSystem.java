package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

@Entity
public class ComputerSystem extends ExternalAssetBase {
    private String name;
    private String domainName;
    private String modelName;
    private String systemType;
    private String pcSystemType;
    private String uuid;
    private String bootUpState;
    private int numberOfLogicalProcessor;
    private int numberOfProcessors;

    private String partOfDomian;
    private String userName;

}
