package com.serviceops.assetdiscovery.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serviceops.assetdiscovery.entity.enums.IpRangeType;
import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.List;

public class NetworkScanRest extends SingleBaseRest {
    private String name;
    @Enumerated(EnumType.STRING)
    private ScanType scanType;
    private Long schedulerRefId;
    @Enumerated(EnumType.STRING)
    private IpRangeType ipRangeType;
    private List<String> refIds;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDateTime lastScan;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")

    private LocalDateTime nextScan;
    private boolean isEnabled;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public LocalDateTime getLastScan() {
        return lastScan;
    }

    public void setLastScan(LocalDateTime lastScan) {
        this.lastScan = lastScan;
    }

    public LocalDateTime getNextScan() {
        return nextScan;
    }

    public void setNextScan(LocalDateTime nextScan) {
        this.nextScan = nextScan;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public IpRangeType getIpRangeType() {
        return ipRangeType;
    }

    public void setIpRangeType(IpRangeType ipRangeType) {
        this.ipRangeType = ipRangeType;
    }

    public List<String> getRefIds() {
        return refIds;
    }

    public void setRefIds(List<String> refIds) {
        this.refIds = refIds;
    }

    public Long getSchedulerRefId() {
        return schedulerRefId;
    }

    public void setSchedulerRefId(Long schedulerRefId) {
        this.schedulerRefId = schedulerRefId;
    }
}
