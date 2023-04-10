package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class ComputerSystem extends AssetBase {
    private String description;
    private String modelName;
    private String systemType;
    private String pcSystemType;
    private String uuid;
    private String bootUpState;
    private String partOfDomian;
    private String userName;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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

    public String getPcSystemType() {
        return pcSystemType;
    }

    public void setPcSystemType(String pcSystemType) {
        this.pcSystemType = pcSystemType;
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
    public String getPartOfDomian() {
        return partOfDomian;
    }
    public void setPartOfDomian(String partOfDomian) {
        this.partOfDomian = partOfDomian;
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

        ComputerSystem that = (ComputerSystem) o;

        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getModelName() != null ? !getModelName().equals(that.getModelName()) : that.getModelName() != null)
            return false;
        if (getSystemType() != null ? !getSystemType().equals(that.getSystemType()) : that.getSystemType() != null)
            return false;
        if (getPcSystemType() != null ? !getPcSystemType().equals(that.getPcSystemType()) : that.getPcSystemType() != null)
            return false;
        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;
        if (getBootUpState() != null ? !getBootUpState().equals(that.getBootUpState()) : that.getBootUpState() != null)
            return false;
        if (getPartOfDomian() != null ? !getPartOfDomian().equals(that.getPartOfDomian()) : that.getPartOfDomian() != null)
            return false;
        return getUserName() != null ? getUserName().equals(that.getUserName()) : that.getUserName() == null;
    }

    @Override
    public int hashCode() {
        int result = getDescription() != null ? getDescription().hashCode() : 0;
        result = 31 * result + (getModelName() != null ? getModelName().hashCode() : 0);
        result = 31 * result + (getSystemType() != null ? getSystemType().hashCode() : 0);
        result = 31 * result + (getPcSystemType() != null ? getPcSystemType().hashCode() : 0);
        result = 31 * result + (getUuid() != null ? getUuid().hashCode() : 0);
        result = 31 * result + (getBootUpState() != null ? getBootUpState().hashCode() : 0);
        result = 31 * result + (getPartOfDomian() != null ? getPartOfDomian().hashCode() : 0);
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComputerSystem{" +
                ", description='" + description + '\'' +
                ", modelName='" + modelName + '\'' +
                ", systemType='" + systemType + '\'' +
                ", pcSystemType='" + pcSystemType + '\'' +
                ", uuid='" + uuid + '\'' +
                ", bootUpState='" + bootUpState + '\'' +
                ", partOfDomian='" + partOfDomian + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
