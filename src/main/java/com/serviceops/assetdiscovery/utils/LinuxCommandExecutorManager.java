package com.serviceops.assetdiscovery.utils;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.entity.base.AuditBase;
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
    private static final HashMap<Class<? extends AuditBase>, LinkedHashMap<String, String[]>>
            commandResults = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(LinuxCommandExecutorManager.class);
    private final String hostname;
    private final String password;
    private final String username;
    private final int port;

    public LinuxCommandExecutorManager(String hostname, String username, String password, int port) {
        this.hostname = hostname;
        this.password = password;
        this.username = username;
        this.port = port;
    }

    public static boolean testConnection(String hostname, String username, String password, int port) {
        try (LinuxCommandExecutor linuxCommandExecutor = new LinuxCommandExecutor(hostname, username,
                PasswordEncoderSSH.encryptPassword(password), port)) {
            return true;
        } catch (Exception e) {
            logger.error("Auth fail -> {}", hostname);
            return false;
        }
    }

    public static <T extends AuditBase> void add(Class<T> key, LinkedHashMap<String, String[]> hashMap) {
        commandResults.put(key, hashMap);
    }

    public static <T extends AuditBase> Map<String, String[]> get(Class<T> key) {
        return commandResults.get(key);
    }

    public void fetch() throws AssetDiscoveryApiException {
        try (LinuxCommandExecutor linuxCommandExecutor = new LinuxCommandExecutor(hostname, username,
                password, port)) {
            for (Map.Entry<Class<? extends AuditBase>, LinkedHashMap<String, String[]>> commandResultsLocal : commandResults.entrySet()) {
                LinkedHashMap<String, String[]> commands = commandResultsLocal.getValue();
                for (Map.Entry<String, String[]> entry : commands.entrySet()) {
                    String[] result = linuxCommandExecutor.execute(entry.getKey());
                    commands.put(entry.getKey(), result);
                }
            }
        } catch (JSchException | IOException e) {
            logger.error("AssetDiscoveryApiException -> {}", e.getMessage());
            throw new AssetDiscoveryApiException(e.getMessage());
        }
    }

}
