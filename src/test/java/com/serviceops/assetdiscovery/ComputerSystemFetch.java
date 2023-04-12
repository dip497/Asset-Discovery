package com.serviceops.assetdiscovery;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.impl.ComputerSystemServiceImpl;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.ComputerSystemOps;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ComputerSystemFetch {

    @Autowired
    ComputerSystemService computerSystemService;
    private Logger logger = LoggerFactory.getLogger(ComputerSystem.class);
    @Test
    public void  fetchData() throws JSchException, IOException {

        LinuxCommandExecutorManager linuxCommandExecutorManager = new LinuxCommandExecutorManager("172.16.12.101","flotomate","admin",22);
        linuxCommandExecutorManager.fetch();

        computerSystemService.save(1L);
        String string = computerSystemService.get(1L).toString();
    }
    @Test
    public void parseResults() throws JSchException, IOException {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        LinuxCommandExecutorManager linuxCommandExecutorManager = new LinuxCommandExecutorManager("172.16.12.101","flotomate","admin",22);
        commands.put("sudo who| head -n1 | cut -d' ' -f1", new String[]{});
        commands.put("sudo dmidecode -s system-product-name", new String[]{});
        commands.put("uname -m", new String[]{});
//        commands.put() set to be later
        commands.put("sudo dmidecode -s system-uuid", new String[]{});
        commands.put("systemctl is-system-running", new String[]{});
        commands.put("sudo dmidecode -s system-manufacturer", new String[]{});
        LinuxCommandExecutorManager.add(ComputerSystem.class, commands);
        linuxCommandExecutorManager.fetch();
        Map<String, String[]> stringMap =  LinuxCommandExecutorManager.get(ComputerSystem.class);
        List<String> parsedResults = new ArrayList<>();
        for (Map.Entry<String, String[]> commandResult : stringMap.entrySet()) {
            String[] results = commandResult.getValue();
            for (int i=0;i< results.length;i++) {
                System.out.println(results[i]);;
            }
        }

    }
    @Test
    public void save(){

    }


}
