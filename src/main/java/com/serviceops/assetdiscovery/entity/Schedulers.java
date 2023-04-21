package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import com.serviceops.assetdiscovery.entity.enums.Month;
import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.entity.enums.Week;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Schedulers extends SingleBase {
    private long networkScanId;
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

    public long getNetworkScanId() {
        return networkScanId;
    }

    public void setNetworkScanId(long networkScanId) {
        this.networkScanId = networkScanId;
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
