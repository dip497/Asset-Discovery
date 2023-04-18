package com.serviceops.assetdiscovery.utils;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.entity.base.SingleBase;
import com.serviceops.assetdiscovery.exception.AssetDiscoveryApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *

 */
public class LinuxCommandExecutorManager {
    private static  final HashMap<Class<? extends SingleBase>, LinkedHashMap<String,String[]>> commandResults  = new HashMap<>();
    private final String hostname;
    private final String password;
    private final String username;
    private final int port;
    private static final Logger logger = LoggerFactory.getLogger(LinuxCommandExecutorManager.class);

    public LinuxCommandExecutorManager(String hostname, String username, String password, int port) {
        this.hostname = hostname;
        this.password = password;
        this.username = username;
        this.port = port;
    }

    public  void fetch() throws AssetDiscoveryApiException {
        try(LinuxCommandExecutor linuxCommandExecutor = new LinuxCommandExecutor(hostname,username,password,port)){
            linuxCommandExecutor.connect();
            for (Map.Entry<Class<? extends SingleBase>, LinkedHashMap<String,String[]>> commandResultsLocal : commandResults.entrySet()){
                LinkedHashMap<String, String[]> commands = commandResultsLocal.getValue();
                for (Map.Entry<String,String[]> entry : commands.entrySet()) {
                    String[] result = linuxCommandExecutor.execute(entry.getKey());
                    commands.put(entry.getKey(), result);
                }
            }
        } catch (JSchException | IOException e) {
            logger.error("AssetDiscoveryApiException -> {}", e.getMessage());
            throw new AssetDiscoveryApiException(e.getMessage());
        }
    }

    public static boolean testConnection(String hostname,String username, String password,int port)   {
        try(LinuxCommandExecutor linuxCommandExecutor = new LinuxCommandExecutor(hostname,username,password,port)){
            return linuxCommandExecutor.connect();
        }catch (Exception e) {
            logger.error("Auth fail -> {}", e.getMessage());
          //  throw new AssetDiscoveryApiException(e.getMessage());
        }
        return false;
    }

    public static <T extends SingleBase> void add(Class<T> key,LinkedHashMap<String,String[]> hashMap){
        commandResults.put(key,hashMap);
    }
    public static <T extends SingleBase> Map<String,String[]> get(Class<T> key){
        return commandResults.get(key);
    }

}
