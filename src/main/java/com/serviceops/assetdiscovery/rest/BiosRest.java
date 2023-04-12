package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Bios} entity
 */
public class BiosRest extends AssetBaseRest implements Serializable {
    private  String name;
    private  String smBiosVersion;
    private  String releaseDate;
    private  String version;
    private  String description;

    public String getName() {
        return name;
    }

    public String getSmBiosVersion() {
        return smBiosVersion;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSmBiosVersion(String smBiosVersion) {
        this.smBiosVersion = smBiosVersion;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiosRest entity = (BiosRest) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.smBiosVersion, entity.smBiosVersion) &&
                Objects.equals(this.releaseDate, entity.releaseDate) &&
                Objects.equals(this.version, entity.version) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, smBiosVersion, releaseDate, version, description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "smBiosVersion = " + smBiosVersion + ", " +
                "realeaseDate = " + releaseDate + ", " +
                "version = " + version + ", " +
                "description = " + description + ")";
    }
}