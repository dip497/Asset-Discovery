package com.serviceops.assetdiscovery.utils;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.entity.base.SingleBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 *

 */
public class LinuxCommandExecutorManager {
    private static  final HashMap<Class<? extends SingleBase>, HashMap<String,String[]>> commandResults  = new HashMap<>();
    LinuxCommandExecutor linuxCommandExecutor;
    private final String hostname;
    private final String password;
    private final String username;
    private final int port;
    public LinuxCommandExecutorManager(String hostname, String username, String password, int port) {
        linuxCommandExecutor = new LinuxCommandExecutor() ;
        this.hostname = hostname;
        this.password = password;
        this.username = username;
        this.port = port;
    }
    // TODO : handle Exception
    public  void fetch() throws JSchException, IOException {
        linuxCommandExecutor.connect(hostname,username,password,port);
        for (Map.Entry<Class<? extends SingleBase>, HashMap<String,String[]>> commandResultsLocal : commandResults.entrySet()){
            HashMap<String, String[]> commands = commandResultsLocal.getValue();
            for (Map.Entry<String,String[]> entry : commands.entrySet()) {
                String[] result = linuxCommandExecutor.execute(entry.getKey());
                commands.put(entry.getKey(), result);
            }
        }
        linuxCommandExecutor.disconnect();
    }

    public static <T extends SingleBase> void add(Class<T> key,HashMap<String,String[]> hashMap){
        commandResults.put(key,hashMap);
    }
    public static <T extends SingleBase> Map<String,String[]> get(Class<T> key){
        return commandResults.get(key);
    }

}
