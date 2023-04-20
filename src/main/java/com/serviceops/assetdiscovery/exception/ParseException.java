package com.serviceops.assetdiscovery.exception;

public class ParseException extends RuntimeException {
    private String resourceName;

    public ParseException(String resourceName) {
        super("fail to parse result of " + resourceName);
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
