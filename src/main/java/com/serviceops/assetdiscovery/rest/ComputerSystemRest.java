package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AuditBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.ComputerSystem} entity
 */
public class ComputerSystemRest extends AuditBaseRest implements Serializable {
    private long refId;
    private String name;
    private String modelName;
    private String systemType;
    private String uuid;
    private String bootUpState;
    private long numberOfLogicalProcessor;
    private long numberOfProcessors;
    private String userName;
    private String manufacturer;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getNumberOfLogicalProcessor() {
        return numberOfLogicalProcessor;
    }

    public void setNumberOfLogicalProcessor(long numberOfLogicalProcessor) {
        this.numberOfLogicalProcessor = numberOfLogicalProcessor;
    }

    public long getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public void setNumberOfProcessors(long numberOfProcessors) {
        this.numberOfProcessors = numberOfProcessors;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ComputerSystemRest that = (ComputerSystemRest) o;
        return Objects.equals(name, that.name) && Objects.equals(modelName, that.modelName) && Objects.equals(
                systemType, that.systemType) && Objects.equals(uuid, that.uuid) && Objects.equals(bootUpState,
                that.bootUpState) && Objects.equals(numberOfLogicalProcessor, that.numberOfLogicalProcessor)
                && Objects.equals(numberOfProcessors, that.numberOfProcessors) && Objects.equals(userName,
                that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, modelName, systemType, uuid, bootUpState,
                numberOfLogicalProcessor, numberOfProcessors, userName);
    }

    @Override
    public String toString() {
        return "ComputerSystemRest{" + "name='" + name + '\'' + ", modelName='" + modelName + '\''
                + ", systemType='" + systemType + '\'' + ", uuid='" + uuid + '\'' + ", bootUpState='"
                + bootUpState + '\'' + ", numberOfLogicalProcessor=" + numberOfLogicalProcessor
                + ", numberOfProcessors=" + numberOfProcessors + ", userName='" + userName + '\'' + '}';
    }
}