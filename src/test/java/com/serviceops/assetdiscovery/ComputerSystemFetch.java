package com.serviceops.assetdiscovery;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.impl.ComputerSystemServiceImpl;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.ComputerSystemOps;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class ComputerSystemFetch {

    @Autowired
    ComputerSystemService computerSystemService;
    private Logger logger = LoggerFactory.getLogger(ComputerSystem.class);
    @Test
    public void  fetchData() throws JSchException, IOException {
        ComputerSystemOps.setCommands();
        LinuxCommandExecutorManager linuxCommandExecutorManager = new LinuxCommandExecutorManager("172.16.12.101","flotomate","admin",22);
        linuxCommandExecutorManager.fetch();
        List<String> parseResults = ComputerSystemOps.getParseResults();
        computerSystemService.save(1L);
        String string = computerSystemService.getComputerSystem(1L).toString();
    }
    @Test
    public void parseResults() throws JSchException, IOException {
        ComputerSystemOps.setCommands();
        LinuxCommandExecutorManager linuxCommandExecutorManager = new LinuxCommandExecutorManager("172.16.12.101","flotomate","admin",22);
        linuxCommandExecutorManager.fetch();
        List<String> parseResults = ComputerSystemOps.getParseResults();
        parseResults.forEach(System.out::println);

    }


}
