package com.serviceops.assetdiscovery.utils;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LinuxCommandExecutor {

    private Session session;


    public void connect(String host, String username, String password) throws JSchException {
        System.out.println("yes");
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, 22);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        System.out.println("yes");
    }

    public String[] execute(String sudoCommand) throws JSchException, IOException {
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand("sudo -S -p '' " + sudoCommand);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();
        OutputStream out = channel.getOutputStream();

        ((ChannelExec) channel).setPty(true);
        channel.connect();

        out.write(("admin" + "\n").getBytes());
        out.flush();

        byte[] tmp = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                sb.append(new String(tmp, 0, i));
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
        session.disconnect();
    }
}