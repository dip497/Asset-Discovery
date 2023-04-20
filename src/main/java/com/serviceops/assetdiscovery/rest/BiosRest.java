package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Bios} entity
 */
public class BiosRest extends AssetBaseRest implements Serializable {
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BiosRest biosRest = (BiosRest) o;
        return Objects.equals(smBiosVersion, biosRest.smBiosVersion) && Objects.equals(releaseDate,
                biosRest.releaseDate) && Objects.equals(version, biosRest.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), smBiosVersion, releaseDate, version);
    }

    @Override
    public String toString() {
        return "BiosRest{" + "smBiosVersion='" + smBiosVersion + '\'' + ", releaseDate=" + releaseDate
                + ", version='" + version + '\'' + '}';
    }
}