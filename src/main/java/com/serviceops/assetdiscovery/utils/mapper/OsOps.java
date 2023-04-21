package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.OS;
import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class OsOps extends SingleBaseOps<OS, OSRest> {

    @Override
    public OS restToEntity(OS os,OSRest osRest) {
        super.restToEntity(os,osRest);
        os.setRefId(osRest.getRefId());
        os.setOsName(osRest.getOsName());
        os.setOsVersion(osRest.getOsVersion());
        os.setArchitecture(osRest.getArchitecture());
        os.setInstalledDate(osRest.getInstalledDate());
        return os;
    }

    @Override
    public OSRest entityToRest(OS os,OSRest osRest) {
        super.entityToRest(os,osRest);
        osRest.setRefId(os.getRefId());
        osRest.setOsName(os.getOsName());
        osRest.setOsVersion(os.getOsVersion());
        osRest.setArchitecture(os.getArchitecture());
        osRest.setInstalledDate(os.getInstalledDate());
        return osRest;
    }


}
