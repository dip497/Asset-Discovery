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
    private Long networkScanId;
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

    public Long getNetworkScanId() {
        return networkScanId;
    }

    public void setNetworkScanId(Long networkScanId) {
        this.networkScanId = networkScanId;
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

        Schedulers that = (Schedulers) o;

        if (getNetworkScanId() != null ?
                !getNetworkScanId().equals(that.getNetworkScanId()) :
                that.getNetworkScanId() != null)
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
        result = 31 * result + (getNetworkScanId() != null ? getNetworkScanId().hashCode() : 0);
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
        return "Schedulers{" + "networkScanId=" + networkScanId + ", scanType=" + scanType + ", startTime="
                + startTime + ", time=" + time + ", date=" + date + ", week=" + week + ", month=" + month
                + ", interval=" + interval + "} " + super.toString();
    }
}
