package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.rest.base.BaseRest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.time.*;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Scheduler} entity
 */
public class SchedulerRest extends BaseRest implements Serializable {
    @Enumerated(EnumType.STRING)
    private ScanType scanType;
    private LocalDateTime startTime;
    private LocalTime time;
    private LocalDate date;
    private Month month;
    private Duration interval;

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Duration getInterval() {
        return interval;
    }

    public void setInterval(Duration interval) {
        this.interval = interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulerRest entity = (SchedulerRest) o;
        return Objects.equals(this.scanType, entity.scanType) &&
                Objects.equals(this.startTime, entity.startTime) &&
                Objects.equals(this.time, entity.time) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.month, entity.month) &&
                Objects.equals(this.interval, entity.interval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scanType, startTime, time, date, month, interval);
    }

    @Override
    public String toString() {
        return "SchedulerRest{" +
                "scanType=" + scanType +
                ", startTime=" + startTime +
                ", time=" + time +
                ", date=" + date +
                ", month=" + month +
                ", interval=" + interval +
                "} " + super.toString();
    }
}