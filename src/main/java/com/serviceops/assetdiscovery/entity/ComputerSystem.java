package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import jakarta.persistence.Entity;

@Entity
public class ComputerSystem extends SingleBase {
    private long refId;
    private String modelName;
    private String systemType;
    private String uuid;
    private String bootUpState;
    private String userName;
    private String manufacturer;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBootUpState() {
        return bootUpState;
    }

    public void setBootUpState(String bootUpState) {
        this.bootUpState = bootUpState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "ComputerSystem{" + "refId=" + refId + ", modelName='" + modelName + '\'' + ", systemType='"
                + systemType + '\'' + ", uuid='" + uuid + '\'' + ", bootUpState='" + bootUpState + '\''
                + ", userName='" + userName + '\'' + ", manufacturer='" + manufacturer + '\'' + '}';
    }
}
