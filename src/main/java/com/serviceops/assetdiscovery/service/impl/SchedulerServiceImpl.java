package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Scheduler;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.SchedulerService;
import com.serviceops.assetdiscovery.utils.mapper.SchedulerOps;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SchedulerServiceImpl implements SchedulerService {
    private final CustomRepository customRepository;

    public SchedulerServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    @Override
    public Scheduler save(SchedulerRest schedulerRest) {
        Optional<Scheduler> fetchScheduler = customRepository.findByColumn("id", schedulerRest.getId(), Scheduler.class);
        if(fetchScheduler.isPresent()){
            Scheduler scheduler = fetchScheduler.get();
            scheduler = new SchedulerOps(scheduler, schedulerRest).restToEntity();
            customRepository.save(scheduler);
            return scheduler;
        }else{
            Scheduler scheduler = new Scheduler();
            scheduler = new SchedulerOps(scheduler,schedulerRest).restToEntity();
            customRepository.save(scheduler);
            return scheduler;
        }

    }

    @Override
    public Scheduler findById(Long id) {
        Optional<Scheduler> scheduler = customRepository.findByColumn("id", id, Scheduler.class);
        if(scheduler.isPresent()){
            return scheduler.get();
        }else{
            throw new ResourceNotFoundException("scheduler","id",id.toString());
        }
    }
}
