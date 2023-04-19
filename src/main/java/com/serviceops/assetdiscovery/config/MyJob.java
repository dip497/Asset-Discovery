package com.serviceops.assetdiscovery.config;

import com.serviceops.assetdiscovery.exception.AssetDiscoveryApiException;
import com.serviceops.assetdiscovery.repository.PersistToDB;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class MyJob implements Job {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("job");

    }





}
