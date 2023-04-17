package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.Base;
import com.serviceops.assetdiscovery.entity.enums.ScanType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.*;
@Entity
public class Scheduler extends Base {
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

    public void setstartTime(LocalDateTime startTime) {
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
}
