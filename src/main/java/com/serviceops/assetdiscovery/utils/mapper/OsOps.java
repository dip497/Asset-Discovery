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

    public OS restToEntity(){
        super.restToEntity(osRest);
        os.setOsName(osRest.getOsName());
        os.setOsVersion(osRest.getOsVersion());
        os.setOsArchitecture(osRest.getOsArchitecture());
        os.setInstalledDate(osRest.getInstalledDate());
        return os;
    }

    public OSRest entityToRest(){
        super.entityToRest(os);
        osRest.setOsName(os.getOsName());
        osRest.setOsVersion(os.getOsVersion());
        osRest.setOsArchitecture(os.getOsArchitecture());
        osRest.setInstalledDate(os.getInstalledDate());
        return osRest;
    }


}
