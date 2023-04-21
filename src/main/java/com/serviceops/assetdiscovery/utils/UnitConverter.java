package com.serviceops.assetdiscovery.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitConverter {
    private UnitConverter() {

    }

    public static long convertToBytes(String data) {
        if (data != null) {
            String pattern = "^\\d*\\.?\\d*";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(data);
            if (m.find() && !m.group().trim().isEmpty()) {
                if (data.contains("M")) {
                    return (long) Double.parseDouble(m.group()) * 1048576;
                } else if (data.contains("G")) {
                    return (long) Double.parseDouble(m.group()) * 1073741824;
                } else if (data.contains("bi")) {
                    return (long) Double.parseDouble(m.group()) / 8;
                }
            }
        }
        return 0l;
    }

}
