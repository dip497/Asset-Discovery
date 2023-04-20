package com.serviceops.assetdiscovery.exception;


public class AssetDiscoveryApiException extends RuntimeException {
    private final String message;

    public AssetDiscoveryApiException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
