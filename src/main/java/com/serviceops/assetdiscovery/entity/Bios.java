package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class Bios extends AssetBase {
    private String name;
    private String smBiosVersion;
    private String releaseDate;
    private String version;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmBiosVersion() {
        return smBiosVersion;
    }

    public void setSmBiosVersion(String smBiosVersion) {
        this.smBiosVersion = smBiosVersion;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bios bios = (Bios) o;

        if (getName() != null ? !getName().equals(bios.getName()) : bios.getName() != null) return false;
        if (getSmBiosVersion() != null ? !getSmBiosVersion().equals(bios.getSmBiosVersion()) : bios.getSmBiosVersion() != null)
            return false;
        if (getReleaseDate() != null ? !getReleaseDate().equals(bios.getReleaseDate()) : bios.getReleaseDate() != null)
            return false;
        if (getVersion() != null ? !getVersion().equals(bios.getVersion()) : bios.getVersion() != null) return false;
        return getDescription() != null ? getDescription().equals(bios.getDescription()) : bios.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSmBiosVersion() != null ? getSmBiosVersion().hashCode() : 0);
        result = 31 * result + (getReleaseDate() != null ? getReleaseDate().hashCode() : 0);
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bios{" +
                "name='" + name + '\'' +
                ", smBiosVersion='" + smBiosVersion + '\'' +
                ", releaseDate=" + releaseDate +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
