package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.Schedulers;
import com.serviceops.assetdiscovery.entity.enums.Month;
import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.entity.enums.Week;
import com.serviceops.assetdiscovery.rest.base.AuditBaseRest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;


/**
 * A Rest for the {@link Schedulers} entity
 */
public class SchedulerRest extends AuditBaseRest implements Serializable {
    private long networkScanRestId;
    @Enumerated(EnumType.STRING)
    private ScanType scanType;
    private long startTime;
    private long time;
    private long date;
    @Enumerated(EnumType.STRING)
    private Week week;
    @Enumerated(EnumType.STRING)
    private Month month;
    private long interval;

    public long getNetworkScanRestId() {
        return networkScanRestId;
    }

    public void setNetworkScanRestId(long networkScanRestId) {
        this.networkScanRestId = networkScanRestId;
    }

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}