package com.serviceops.assetdiscovery.entity.enums;

public enum Week {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);
    private final int code;

    Week(int code) {
        this.code = code;
    }

    public int toInt() {
        return code;
    }

    @Override
    public String toString() {
        //only override toString, if the returned value has a meaning for the
        //human viewing this value
        return String.valueOf(code);
    }
}
