package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.NetworkScan;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

import java.util.Arrays;
import java.util.stream.Stream;

public class NetworkScanOps extends SingleBaseOps<NetworkScan, NetworkScanRest> {
    private final NetworkScan networkScan;
    private final NetworkScanRest networkScanRest;

    public NetworkScanOps(NetworkScan networkScan, NetworkScanRest networkScanRest) {
        super(networkScan, networkScanRest);
        this.networkScan = networkScan;
        this.networkScanRest = networkScanRest;
    }

    public NetworkScanRest entityToRest() {
        super.entityToRest(networkScan);
        networkScanRest.setName(networkScan.getName());
        networkScanRest.setLastScan(networkScan.getLastScan());
        networkScanRest.setNextScan(networkScan.getNextScan());
        networkScanRest.setEnabled(networkScan.isEnabled());
        networkScanRest.setIpRangeType(networkScan.getIpRangeType());
        networkScanRest.setIpRangeStart(networkScan.getIpRangeStart());
        networkScanRest.setIpList(Arrays.stream(networkScan.getIpList().split(",")).toList());
        networkScanRest.setCredentialId(Stream.of(networkScan.getCredentialId().split(",")).map(e -> {
            if (e.isEmpty()) {
                return Long.valueOf(0);
            } else {
                return Long.valueOf(e);
            }
        }).toList());
        return networkScanRest;
    }

    public NetworkScan restToEntity() {
        super.restToEntity(networkScanRest);
        networkScan.setName(networkScanRest.getName());
        networkScan.setLastScan(networkScanRest.getLastScan());
        networkScan.setNextScan(networkScanRest.getNextScan());
        networkScan.setEnabled(networkScanRest.isEnabled());
        networkScan.setIpRangeType(networkScanRest.getIpRangeType());
        if (networkScanRest.getIpList() == null) {
            networkScan.setIpList("");
        } else {
            networkScan.setIpList(String.join(",", networkScanRest.getIpList()));
        }
        networkScan.setIpRangeStart(networkScanRest.getIpRangeStart());
        networkScan.setCredentialId(String.join(",", networkScanRest.getCredentialId().stream().map(e -> {
            if (e.equals(0L)) {
                return "";
            } else {
                return String.valueOf(e);
            }
        }).toList()));
        return networkScan;
    }

}
