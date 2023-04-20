package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.config.SchedulerService;
import com.serviceops.assetdiscovery.entity.Schedulers;
import com.serviceops.assetdiscovery.exception.ResourceAlreadyExistsException;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.SchedulersService;
import com.serviceops.assetdiscovery.utils.mapper.SchedulerOps;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchedulerServiceImpl implements SchedulersService {
    private final CustomRepository customRepository;
    private final SchedulerService schedulerService;
    private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    public SchedulerServiceImpl(CustomRepository customRepository, SchedulerService schedulerService) {
        this.customRepository = customRepository;
        this.schedulerService = schedulerService;
    }

    @Override
    public void save(Long networkScanId, SchedulerRest schedulerRest) {
        Optional<Schedulers> fetchScheduler = customRepository.findByColumn("networkScanId", networkScanId, Schedulers.class);
        if (fetchScheduler.isPresent()) {
            logger.error("Scheduler already exists with networkScanId -> {} ", networkScanId);
            throw new ResourceAlreadyExistsException("Scheduler", "networkScanId", networkScanId.toString());

        } else {
            Schedulers schedulers = new Schedulers();
            schedulerRest.setNetworkScanRestId(networkScanId);
            schedulers = new SchedulerOps(schedulers, schedulerRest).restToEntity();
            customRepository.save(schedulers);
            logger.info("Created scheduler with id ->{}", schedulers.getId());
            try {

                schedulerRest.setId(schedulers.getId());
                schedulerService.scheduleJob(schedulerRest);
            } catch (SchedulerException e) {
                logger.error("fail to add scheduler for id ->{}", schedulerRest.getId());
            }


        }

    }
    public void update(Long networkScanId,Long id,SchedulerRest schedulerRest){
        Optional<Schedulers> fetchSchedulers = customRepository.findByColumn("networkScanId", networkScanId, Schedulers.class);
        if(fetchSchedulers.isEmpty()){
            logger.error("Scheduler not exist with networkScanId -> {} ", networkScanId);
            throw new ResourceNotFoundException("Scheduler", "networkScanId", networkScanId.toString());
        }else{
           Schedulers schedulers  = fetchSchedulers.get();
           schedulers = new  SchedulerOps(schedulers,schedulerRest).restToEntity();
           customRepository.save(schedulers);
        }

    }

    @Override
    public SchedulerRest findByNetworkScanId(Long networkScanId) {
        Optional<Schedulers> fetchScheduler = customRepository.findByColumn("networkScanId", networkScanId, Schedulers.class);
        if(fetchScheduler.isPresent()){
            SchedulerRest schedulerRest = new SchedulerRest();
            schedulerRest = new SchedulerOps(fetchScheduler.get(), schedulerRest).entityToRest();
            logger.info("found scheduler with networkScanId -> {}", schedulerRest.getNetworkScanRestId());
            return schedulerRest;
        }else{
            logger.error("Scheduler not exist with networkScanId -> {} ", networkScanId);
            throw  new ResourceNotFoundException("Scheduler","networkScanId", networkScanId.toString());
        }
    }

    @Override
    public List<SchedulerRest> findAll() {
        List<Schedulers> schedulersList = customRepository.findAll(Schedulers.class);
        return schedulersList.stream().map(e -> new SchedulerOps(e, new SchedulerRest()).entityToRest()).toList();
    }
}
