package com.serviceops.assetdiscovery.utils;

public class UnitConverter {
    private UnitConverter(){

    }
    public static Long convertToBytes(String data){
        if(data!=null) {
            String toConvert = data.replaceAll("[^0-9]", "");
            if (!toConvert.trim().isEmpty()) {
                if (data.contains("M")) {
                    return Long.parseLong(toConvert) * 1048576;
                } else if (data.contains("G")) {
                    return Long.parseLong(toConvert) * 1073741824;
                } else if (data.contains("bi")) {
                    return Long.parseLong(toConvert) / 8;
                }
            }
        }
        return 0l;
    }
//    public static Long convertToHertz(String data){
//        String toConvert = data.replaceAll("[^0-9]", "");
//        if(!toConvert.trim().isEmpty()){
//            return Long.parseLong(toConvert)
//        }
//    }
}
