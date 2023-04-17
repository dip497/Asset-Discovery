package com.serviceops.assetdiscovery.config;

import com.serviceops.assetdiscovery.entity.Scheduler;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.NetworkScanService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@EnableAsync
public class NetworkScanScheduler {
    private final NetworkScanService networkScanService;
    private final NetworkScanner networkScanner;
    private final Logger logger = LoggerFactory.getLogger(NetworkScanScheduler.class);

    public NetworkScanScheduler(NetworkScanService networkScanService, NetworkScanner networkScanner) {
        this.networkScanService = networkScanService;
        this.networkScanner = networkScanner;
        logger.info("NetworkScanSceduler initialized");
    }

  // @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(fixedDelayString ="PT2M")// Run daily at midnight
    public void scanNetworkRanges() throws InterruptedException {
        logger.debug("Running scheduled scan ...");
        List<NetworkScanRest> networkScansRest = networkScanService.findAll();
        for (NetworkScanRest networkScanRest : networkScansRest) {
            if (networkScanRest.isEnabled() && networkScanRest.getScanType() != null && networkScanRest.getSchedulerRefId() != null) {
                switch (networkScanRest.getScanType()) {
                    case DAILY ->
                        // Scan every day
                            networkScanner.scanNetworkRange(networkScanRest);
                    case WEEKLY -> {
                        // Scan every week
                        if (networkScanRest.getLastScan().plusWeeks(1).isBefore(LocalDateTime.now())) {
                            networkScanner.scanNetworkRange(networkScanRest);
                        }
                    }
                    case MONTHLY -> {
                        // Scan every month
                        if (networkScanRest.getLastScan().plusMonths(1).isBefore(LocalDateTime.now())) {
                            networkScanner.scanNetworkRange(networkScanRest);
                        }
                    }
                    case INTERVAL -> {
                        // Scan at specified intervals
                        if (networkScanRest.getLastScan().plusMinutes(30).isBefore(LocalDateTime.now())) {
                            networkScanner.scanNetworkRange(networkScanRest);
                        }
                    }
                }
            }
        }
    }
}
/*


*/
/*
 case DAILY -> {
                        logger.debug("daily not implemented");

                        if((scheduler
                                .getTime()
                                .isAfter(LocalTime.now())
                                &&
                                !networkScanRest
                                        .getLastScan()
                                        .isEqual(LocalDate.now().atStartOfDay())
                        ))
                        {
                                networkScanner.scanNetworkRange(networkScanRest);
                        }*//*


                    }
                            case WEEKLY -> {
                            // Scan every week
                       */
/* if (networkScanRest.getCreatedTime().toLocalDateTime().plusWeeks(1).isBefore(LocalDateTime.now())) {
                            if(networkScanRest.getLastScan().isBefore(LocalDateTime.now())){
                                networkScanner.scanNetworkRange(networkScanRest);

                            }
                        }*//*

                            logger.debug("weekly not implemented");

                            }
                            case MONTHLY -> {
                            // Scan every month
                    */
/*    if (networkScanRest.getCreatedTime().toLocalDateTime().plusMonths(1).isBefore(LocalDateTime.now())) {
                            if(networkScanRest.getLastScan().isBefore(LocalDateTime.now())){
                                networkScanner.scanNetworkRange(networkScanRest);
                                networkScanner.scanNetworkRange(networkScanRest);
                            }
                        }*//*

                            logger.debug("monthly not implemented");
                            }
                            case INTERVAL -> {
                            System.out.println("in interval");
                            if (networkScanRest.getLastScan().plusMinutes(2).isBefore(LocalDateTime.now())) {
                            networkScanner.scanNetworkRange(networkScanRest);
                            }
                       */
/* Scheduler scheduler = networkScanService.getSchedulerById(networkScanRest.getSchedulerRefId());
                        System.out.println("in daily");
                        System.out.println(networkScanRest.getCreatedTime().toLocalDateTime().plusMinutes(scheduler.getInterval().toMinutes()));
                        // Scan at specified intervals
                        if (networkScanRest.getCreatedTime().toLocalDateTime().plusMinutes(scheduler.getInterval().toMinutes()).isBefore(LocalDateTime.now())) {
                            if (networkScanRest.getLastScan().isBefore(LocalDateTime.now())) {
                                networkScanner.scanNetworkRange(networkScanRest);
                            }
                        }*//*

 //                           }
/*/
