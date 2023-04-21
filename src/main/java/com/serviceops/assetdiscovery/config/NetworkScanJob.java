package com.serviceops.assetdiscovery.config;

import com.serviceops.assetdiscovery.service.interfaces.NetworkScanService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NetworkScanJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(NetworkScanJob.class);

    @Autowired
    private NetworkScanService networkScanService;

    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        long id = (long) jobDataMap.get("id");
        logger.info("Executing job for network scan id ->{}", id);
        networkScanService.scan(id);
    }

}
