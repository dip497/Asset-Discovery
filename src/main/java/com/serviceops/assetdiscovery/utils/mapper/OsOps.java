package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.OS;
import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class OsOps extends AssetBaseOps<OS, OSRest> {

    private final OS os;
    private final OSRest osRest;

    public OsOps(OS os, OSRest osRest) {
        super(os, osRest);
        this.os = os;
        this.osRest = osRest;
    }




}
