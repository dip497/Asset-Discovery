package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Bios extends AssetBase {
    private String smBiosVersion;
    private Long releaseDate;
    private String version;

    public String getSmBiosVersion() {
        return smBiosVersion;
    }

    public void setSmBiosVersion(String smBiosVersion) {
        this.smBiosVersion = smBiosVersion;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bios bios = (Bios) o;
        return Objects.equals(smBiosVersion, bios.smBiosVersion) && Objects.equals(releaseDate, bios.releaseDate) && Objects.equals(version, bios.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), smBiosVersion, releaseDate, version);
    }

    @Override
    public String toString() {
        return "Bios{" +
                ", smBiosVersion='" + smBiosVersion + '\'' +
                ", releaseDate=" + releaseDate +
                ", version='" + version + '\'' +
                '}';
    }
}
