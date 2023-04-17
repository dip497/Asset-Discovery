package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.NetworkScan;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

import java.util.List;
import java.util.stream.Stream;

public class NetworkScanOps extends SingleBaseOps<NetworkScan, NetworkScanRest> {
    private final NetworkScan networkScan;
    private final NetworkScanRest networkScanRest;
    public NetworkScanOps(NetworkScan networkScan, NetworkScanRest networkScanRest) {
        super(networkScan, networkScanRest);
        this.networkScan = networkScan;
        this.networkScanRest = networkScanRest;
    }
    public NetworkScanRest entityToRest(){
        super.entityToRest(networkScan);
        networkScanRest.setName(networkScan.getName());
        networkScanRest.setScanType(networkScan.getScanType());
        networkScanRest.setLastScan(networkScan.getLastScan());
        networkScanRest.setNextScan(networkScan.getNextScan());
        networkScanRest.setEnabled(networkScan.isEnabled());
        networkScanRest.setIpRangeType(networkScan.getIpRangeType());
        networkScanRest.setRefIds(Stream.of(networkScan.getRefIds().split(",")).map(e->{
            if(e.isEmpty()){
                return Long.valueOf(0);
            }else{
                return Long.valueOf(e);
            }
        }).toList());
        networkScanRest.setSchedulerRefId(networkScan.getSchedulerRefId());
        return networkScanRest;
    }
    public NetworkScan restToEntity(){
        super.restToEntity(networkScanRest);
        networkScan.setName(networkScanRest.getName());
        networkScan.setScanType(networkScanRest.getScanType());
        networkScan.setLastScan(networkScanRest.getLastScan());
        networkScan.setNextScan(networkScanRest.getNextScan());
        networkScan.setEnabled(networkScanRest.isEnabled());
        networkScan.setIpRangeType(networkScanRest.getIpRangeType());
        networkScan.setRefIds(String.join(",",networkScanRest.getRefIds().stream().map(String::valueOf).toList()));
        networkScan.setSchedulerRefId(networkScanRest.getSchedulerRefId());
        return networkScan;
    }

}
