package com.serviceops.assetdiscovery.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class LinuxCommandExecutor implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(LinuxCommandExecutor.class);
    private final String host;
    private final String username;
    private final int port;
    private final String password;
    private Session session;
    private Channel channel;


    public LinuxCommandExecutor(String host, String username, String password, int port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
        connect();
    }

    private void connect() {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(username, host, port);
            session.setPassword(PasswordEncoderSSH.decryptPassword(password));
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            logger.info("Connected to -> {}", host);
        } catch (JSchException e) {
            logger.error("Failed to connect to -> {}", host);
        }
    }

    public String[] execute(String command) throws JSchException, IOException {
        channel = session.openChannel("exec");
        boolean useSudo = command.contains("sudo");
        if (useSudo) {
            ((ChannelExec) channel).setCommand("sudo -S -p '' " + command);
        } else {
            ((ChannelExec) channel).setCommand(command);
        }
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        OutputStream out = channel.getOutputStream();

        ((ChannelExec) channel).setPty(true);
        channel.connect();
        logger.debug("executing command  -> {} ", command);
        if (useSudo) {
            out.write((PasswordEncoderSSH.decryptPassword(password) + "\n").getBytes());
            out.flush();
        }

        byte[] tmp = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0)
                    break;
                String line = new String(tmp, 0, i);
                line = line.replaceAll("[\\p{C}&&[^\n\t]]", "");
                line = line.replaceAll("^\\*+\\s*", "");
                if (line.contains(Objects.requireNonNull(PasswordEncoderSSH.decryptPassword(password))) || line.startsWith("[sudo]")) {
                    in.read(tmp, 0, 1024);
                    continue; // skip the line with the sudo command
                }
                logger.debug("Command output -> {}", line);
                sb.append(line);
            }
            if (channel.isClosed()) {
                if (in.available() > 0)
                    continue;
                break;
            }
        }
        return sb.toString().split("\n");
    }

    @Override
    public void close() {
        if (channel != null) {
            logger.info("Disconnecting for channel of -> {}", host);
            channel.disconnect();
        }
        if (session != null) {
            logger.debug("Disconnect for session of -> {}", host);
            session.disconnect();
        }
    }
}