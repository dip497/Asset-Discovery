package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.entity.enums.Week;
import com.serviceops.assetdiscovery.rest.base.BaseRest;
import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.time.*;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Scheduler} entity
 */
public class SchedulerRest extends SingleBaseRest implements Serializable {
    @Enumerated(EnumType.STRING)
    private ScanType scanType;
    private LocalTime time;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Week week;
    private Month month;
    private Duration interval;

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
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

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SchedulerRest that = (SchedulerRest) o;

        if (getScanType() != that.getScanType()) return false;
        if (getTime() != null ? !getTime().equals(that.getTime()) : that.getTime() != null) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getWeek() != that.getWeek()) return false;
        if (getMonth() != that.getMonth()) return false;
        return getInterval() != null ? getInterval().equals(that.getInterval()) : that.getInterval() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getScanType() != null ? getScanType().hashCode() : 0);
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getWeek() != null ? getWeek().hashCode() : 0);
        result = 31 * result + (getMonth() != null ? getMonth().hashCode() : 0);
        result = 31 * result + (getInterval() != null ? getInterval().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SchedulerRest{" +
                "scanType=" + scanType +
                ", time=" + time +
                ", date=" + date +
                ", week=" + week +
                ", month=" + month +
                ", interval=" + interval +
                "} " + super.toString();
    }
}