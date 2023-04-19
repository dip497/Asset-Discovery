package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class ComputerSystem extends AssetBase {
    private String modelName;
    private String systemType;
    private String uuid;
    private String bootUpState;
    private String userName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComputerSystem that = (ComputerSystem) o;
        return Objects.equals(modelName, that.modelName) && Objects.equals(systemType, that.systemType) && Objects.equals(uuid, that.uuid) && Objects.equals(bootUpState, that.bootUpState) && Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), modelName, systemType, uuid, bootUpState, userName);
    }

    @Override
    public String toString() {
        return "ComputerSystem{" +
                "modelName='" + modelName + '\'' +
                ", systemType='" + systemType + '\'' +
                ", uuid='" + uuid + '\'' +
                ", bootUpState='" + bootUpState + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
