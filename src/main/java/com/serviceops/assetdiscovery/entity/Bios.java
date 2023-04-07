package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class Bios extends ExternalAssetBase{
    private String name;
    private String smBiosVersion;
    private Date realeaseDate;

    private String version;


}
