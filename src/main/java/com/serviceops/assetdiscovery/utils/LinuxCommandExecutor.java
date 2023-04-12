package com.serviceops.assetdiscovery.utils;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LinuxCommandExecutor {
    private Session session;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String password;

    public void connect(String host, String username, String password,int port) throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        this.password = password;
        logger.info("Connected to -> {} ", host ); ;
    }
    public String[] execute(String command) throws JSchException, IOException {
        Channel channel = session.openChannel("exec");
        boolean useSudo = command.contains("sudo");
        if(useSudo){
            ((ChannelExec) channel).setCommand("sudo -S -p '' " + command);
        }
        else{
            ((ChannelExec) channel).setCommand(command);
        }
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream in = channel.getInputStream();
            OutputStream out = channel.getOutputStream();

            ((ChannelExec) channel).setPty(true);
            channel.connect();
            logger.debug("executing command  -> {} ", command);
            if(useSudo) {
                out.write((password + "\n").getBytes());
                out.flush();
            }

            byte[] tmp = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    String line = new String(tmp, 0, i);
                    line = line.replaceAll("[\\p{C}&&[^\n\t]]", "");
                    line = line.replaceAll("^\\*+\\s*", "");
                    if(line.contains(password) || line.startsWith("[sudo]")) {
                        in.read(tmp, 0, 1024);
                        continue; // skip the line with the sudo command
                    }
                    logger.debug("Command output -> {}", line);
                    sb.append(line);
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    break;
                }
            }
            channel.disconnect();
            return sb.toString().split("\n");
        }


    public void disconnect() {
        logger.info("Disconnecting ");
        session.disconnect();
    }
}