package com.serviceops.assetdiscovery.utils;

public class UnitConverter {
    private UnitConverter() {

    }

    public static long convertToBytes(String data) {
        if (data != null) {
            String toConvert = data.replaceAll("^[0-9]+(\\.[0-9]+)?$", "");
            if (!toConvert.trim().isEmpty()) {
                if (data.contains("M")) {
                    return (long)Double.parseDouble(toConvert) * 1048576;
                } else if (data.contains("G")) {
                    return (long)Double.parseDouble(toConvert) * 1073741824;
                } else if (data.contains("bi")) {
                    return (long)Double.parseDouble(toConvert) / 8;
                }
            }
        }
        return 0l;
    }

}
