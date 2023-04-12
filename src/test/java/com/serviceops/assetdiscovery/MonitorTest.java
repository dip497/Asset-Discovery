package com.serviceops.assetdiscovery;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.impl.MonitorServiceImpl;
import com.serviceops.assetdiscovery.service.interfaces.MonitorService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class MonitorTest {


    @Autowired
    CustomRepository customRepository;
    @Test
    public void save() throws JSchException, IOException {
        MonitorServiceImpl monitorService1 = new MonitorServiceImpl(customRepository);
        LinuxCommandExecutorManager linuxCommandExecutorManager = new LinuxCommandExecutorManager("172.16.8.89","motadata","admin",22);
        linuxCommandExecutorManager.fetch();
        monitorService1.save(1l);

    }

}
