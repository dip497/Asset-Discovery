package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.Base;
import com.serviceops.assetdiscovery.entity.base.SingleBase;
import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.entity.enums.Week;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.*;
@Entity
public class Scheduler extends SingleBase {
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

        Scheduler scheduler = (Scheduler) o;

        if (getScanType() != scheduler.getScanType()) return false;
        if (getTime() != null ? !getTime().equals(scheduler.getTime()) : scheduler.getTime() != null) return false;
        if (getDate() != null ? !getDate().equals(scheduler.getDate()) : scheduler.getDate() != null) return false;
        if (getWeek() != scheduler.getWeek()) return false;
        if (getMonth() != scheduler.getMonth()) return false;
        return getInterval() != null ? getInterval().equals(scheduler.getInterval()) : scheduler.getInterval() == null;
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
}
