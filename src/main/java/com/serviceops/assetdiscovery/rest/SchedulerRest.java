package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.Schedulers;
import com.serviceops.assetdiscovery.entity.enums.Month;
import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.entity.enums.Week;
import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;


/**
 * A Rest for the {@link Schedulers} entity
 */
public class SchedulerRest extends SingleBaseRest implements Serializable {
    private Long networkScanRestId;
    @Enumerated(EnumType.STRING)
    private ScanType scanType;
    private Long startTime;
    private Long time;
    private Long date;
    @Enumerated(EnumType.STRING)
    private Week week;
    @Enumerated(EnumType.STRING)
    private Month month;
    private Long interval;

    public Long getNetworkScanRestId() {
        return networkScanRestId;
    }

    public void setNetworkScanRestId(Long networkScanRestId) {
        this.networkScanRestId = networkScanRestId;
    }

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
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

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        SchedulerRest that = (SchedulerRest) o;

        if (getNetworkScanRestId() != null ?
                !getNetworkScanRestId().equals(that.getNetworkScanRestId()) :
                that.getNetworkScanRestId() != null)
            return false;
        if (getScanType() != that.getScanType())
            return false;
        if (getStartTime() != null ?
                !getStartTime().equals(that.getStartTime()) :
                that.getStartTime() != null)
            return false;
        if (getTime() != null ? !getTime().equals(that.getTime()) : that.getTime() != null)
            return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null)
            return false;
        if (getWeek() != that.getWeek())
            return false;
        if (getMonth() != that.getMonth())
            return false;
        return getInterval() != null ? getInterval().equals(that.getInterval()) : that.getInterval() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getNetworkScanRestId() != null ? getNetworkScanRestId().hashCode() : 0);
        result = 31 * result + (getScanType() != null ? getScanType().hashCode() : 0);
        result = 31 * result + (getStartTime() != null ? getStartTime().hashCode() : 0);
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getWeek() != null ? getWeek().hashCode() : 0);
        result = 31 * result + (getMonth() != null ? getMonth().hashCode() : 0);
        result = 31 * result + (getInterval() != null ? getInterval().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SchedulerRest{" + "networkScanRestId=" + networkScanRestId + ", scanType=" + scanType
                + ", startTime=" + startTime + ", time=" + time + ", date=" + date + ", week=" + week
                + ", month=" + month + ", interval=" + interval + "} " + super.toString();
    }
}