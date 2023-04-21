package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AuditBaseRest;

import java.io.Serializable;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Keyboard} entity
 */
public class KeyboardRest extends AuditBaseRest implements Serializable {
    private long refId;
    private String name;


    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}