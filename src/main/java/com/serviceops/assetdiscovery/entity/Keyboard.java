package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

import java.sql.Date;
@Entity
public class Keyboard extends SinglePnpDevice{
    private String name;
    private Date installedDate;


}
