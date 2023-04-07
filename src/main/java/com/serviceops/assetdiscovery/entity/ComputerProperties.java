package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

@Entity
public class ComputerProperties extends SingleAssetBase{
    private String osName;
    private String osVersion;
    private String servicePackName;
    private String osLicenseKey;
    private String osManufacturer;
    private String osArchitecture;
    private String bootUpState;
    private long memorySize;
    private long diskSize;
    private long cpuSpeed;
    private int cpuCoreCount;
    private boolean partOfDomain;
    private String domainName;
    private int numberOfLogicalProcessors;
    private int numberOfProcessors;
    private String pcSystemType;
    private String lastLoggedInUser;
    private String activationStatus;


}
