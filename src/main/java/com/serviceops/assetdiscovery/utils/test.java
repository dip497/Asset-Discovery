package com.serviceops.assetdiscovery.utils;

import com.jcraft.jsch.JSchException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws JSchException, IOException {
        fillHashMap();
        test testp = new test();
        testp.fetch();
        String[] keyboards = test.output.get("Asset").get("sudo ifconfig -a");
        for (int i = 0; i < keyboards.length; i++) {
            System.out.println(keyboards[i]);

        }
    }
    private static HashMap<String, HashMap<String,String[]>> output  = new HashMap<>();

    LinuxCommandExecutor linuxCommandExecutor;

    public test() {
        linuxCommandExecutor = new LinuxCommandExecutor() ;
    }

    static void fillHashMap(){

        HashMap<String , String[]> map = new HashMap<String , String[]>();
        map.put("sudo ifconfig -a", new String[]{});
        map.put("sudo ls", new String[]{});

        HashMap<String , String[]> map2 = new HashMap<String , String[]>();
        map.put("sudo dmidecode -t 2 | grep -e 'Manufacturer'", new String[]{});
        map.put("sudo dmidecode -t 2 | grep -e 'Serial Number'",new String[]{});

        HashMap<String, HashMap<String,String[]>> restMap = new HashMap<>();

        restMap.put("Asset",map);

        output = restMap;
    }

    public  void fetch() throws JSchException, IOException {
        linuxCommandExecutor.connect("172.16.10.78","flotomate","admin");
        for (Map.Entry<String, HashMap<String,String[]>> out1 : output.entrySet()){
            HashMap<String, String[]> stringHashMap = out1.getValue();
            for (Map.Entry<String,String[]> entry : stringHashMap.entrySet()) {
                String[] execute = linuxCommandExecutor.execute(entry.getKey());
                System.out.println(execute);
                stringHashMap.put(entry.getKey(), execute);
            }

        }
        linuxCommandExecutor.disconnect();
    }
}
