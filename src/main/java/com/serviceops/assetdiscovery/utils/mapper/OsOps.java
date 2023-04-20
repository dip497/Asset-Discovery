package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.OS;
import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class OsOps extends SingleBaseOps<OS, OSRest> {

    private final OS os;
    private final OSRest osRest;

    public OsOps(OS os, OSRest osRest) {
        super(os, osRest);
        this.os = os;
        this.osRest = osRest;
    }

    public OS restToEntity() {
        super.restToEntity(osRest);
        os.setRefId(osRest.getRefId());
        os.setOsName(osRest.getOsName());
        os.setOsVersion(osRest.getOsVersion());
        os.setArchitecture(osRest.getArchitecture());
        os.setInstalledDate(osRest.getInstalledDate());
        return os;
    }

    public OSRest entityToRest() {
        super.entityToRest(os);
        osRest.setRefId(os.getRefId());
        osRest.setOsName(os.getOsName());
        osRest.setOsVersion(os.getOsVersion());
        osRest.setArchitecture(os.getArchitecture());
        osRest.setInstalledDate(os.getInstalledDate());
        return osRest;
    }


}
