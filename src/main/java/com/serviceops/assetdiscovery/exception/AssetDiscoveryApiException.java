package com.serviceops.assetdiscovery.exception;


public class AssetDiscoveryApiException extends Exception{
    private String message;
    public AssetDiscoveryApiException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
