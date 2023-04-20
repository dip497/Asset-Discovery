package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class MotherBoard extends AssetBase {
    /*
            motherBoard.setManufacturer(parseResult.get(0));
            motherBoard.setSerialNumber(parseResult.get(1));
            motherBoard.setVersion(parseResult.get(2));*/
    private String version;
    //    private Date installedDate;
    //    private String partNumber;
    //    private String primaryBusType;
    //    private String secondaryBusType;

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
        MotherBoard that = (MotherBoard) o;
        return Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), version);
    }

    @Override
    public String toString() {
        return "MotherBoard{" + "version='" + version + '\'' + '}';
    }
}
