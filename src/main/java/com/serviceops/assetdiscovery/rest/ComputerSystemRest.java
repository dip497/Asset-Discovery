package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.ComputerSystem} entity
 */
public class ComputerSystemRest extends AssetBaseRest implements Serializable {
    private  String name;
    private  String description;
    private  String domainName;
    private  String modelName;
    private  String systemType;
    private  String pcSystemType;
    private  String uuid;
    private  String bootUpState;
    private  int numberOfLogicalProcessor;
    private  int numberOfProcessors;
    private  String partOfDomian;
    private  String userName;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getSystemType() {
        return systemType;
    }

    public String getPcSystemType() {
        return pcSystemType;
    }

    public String getUuid() {
        return uuid;
    }

    public String getBootUpState() {
        return bootUpState;
    }

    public int getNumberOfLogicalProcessor() {
        return numberOfLogicalProcessor;
    }

    public int getNumberOfProcessors() {
        return numberOfProcessors;
    }

    public String getPartOfDomian() {
        return partOfDomian;
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public void setPcSystemType(String pcSystemType) {
        this.pcSystemType = pcSystemType;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setBootUpState(String bootUpState) {
        this.bootUpState = bootUpState;
    }

    public void setNumberOfLogicalProcessor(int numberOfLogicalProcessor) {
        this.numberOfLogicalProcessor = numberOfLogicalProcessor;
    }

    public void setNumberOfProcessors(int numberOfProcessors) {
        this.numberOfProcessors = numberOfProcessors;
    }

    public void setPartOfDomian(String partOfDomian) {
        this.partOfDomian = partOfDomian;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerSystemRest entity = (ComputerSystemRest) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.domainName, entity.domainName) &&
                Objects.equals(this.modelName, entity.modelName) &&
                Objects.equals(this.systemType, entity.systemType) &&
                Objects.equals(this.pcSystemType, entity.pcSystemType) &&
                Objects.equals(this.uuid, entity.uuid) &&
                Objects.equals(this.bootUpState, entity.bootUpState) &&
                Objects.equals(this.numberOfLogicalProcessor, entity.numberOfLogicalProcessor) &&
                Objects.equals(this.numberOfProcessors, entity.numberOfProcessors) &&
                Objects.equals(this.partOfDomian, entity.partOfDomian) &&
                Objects.equals(this.userName, entity.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, domainName, modelName, systemType, pcSystemType, uuid, bootUpState, numberOfLogicalProcessor, numberOfProcessors, partOfDomian, userName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "domainName = " + domainName + ", " +
                "modelName = " + modelName + ", " +
                "systemType = " + systemType + ", " +
                "pcSystemType = " + pcSystemType + ", " +
                "uuid = " + uuid + ", " +
                "bootUpState = " + bootUpState + ", " +
                "numberOfLogicalProcessor = " + numberOfLogicalProcessor + ", " +
                "numberOfProcessors = " + numberOfProcessors + ", " +
                "partOfDomian = " + partOfDomian + ", " +
                "userName = " + userName + ")";
    }
}