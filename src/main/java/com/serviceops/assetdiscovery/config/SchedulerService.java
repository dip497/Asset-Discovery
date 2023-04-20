package com.serviceops.assetdiscovery.config;

import com.serviceops.assetdiscovery.rest.SchedulerRest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SchedulerService {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);
    private final Scheduler scheduler;


    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public static JobDetail buildJobDetail(final Class jobClass, final SchedulerRest info) {
        final JobDataMap jobDataMap = new JobDataMap();
        Long id = info.getNetworkScanRestId();
        jobDataMap.put("id", id);

        return JobBuilder.newJob(jobClass).withIdentity(jobClass.getSimpleName()).setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class jobClass, final SchedulerRest info) {


        TriggerBuilder<Trigger> cronTriggerTriggerBuilder =
                TriggerBuilder.newTrigger().withIdentity(jobClass.getSimpleName())
                        .startAt(new Date(info.getStartTime()));

        switch (info.getScanType()) {
            case DAILY -> cronTriggerTriggerBuilder.withSchedule(
                    CronScheduleBuilder.dailyAtHourAndMinute(getHoursMinutes(info.getTime())[0],
                            getHoursMinutes(info.getTime())[1]));
            case WEEKLY -> cronTriggerTriggerBuilder.withSchedule(
                    CronScheduleBuilder.weeklyOnDayAndHourAndMinute(info.getWeek().toInt(),
                            getHoursMinutes(info.getTime())[0], getHoursMinutes(info.getTime())[1]));
            case MONTHLY -> cronTriggerTriggerBuilder.withSchedule(
                    CronScheduleBuilder.monthlyOnDayAndHourAndMinute(info.getMonth().toInt(),
                            getHoursMinutes(info.getTime())[0], getHoursMinutes(info.getTime())[1]));
            case INTERVAL -> cronTriggerTriggerBuilder.withSchedule(
                    SimpleScheduleBuilder.simpleSchedule().repeatForever()
                            .withIntervalInMinutes(Math.toIntExact(info.getInterval())));
        }

        return cronTriggerTriggerBuilder.build();
    }

    private static int[] getHoursMinutes(Long milliseconds) {
        int seconds = (int) (milliseconds / 1000);
        int hours = seconds / 3600;
        int remainingSeconds = seconds % 3600;
        int minutes = remainingSeconds / 60;
        return new int[] { hours, minutes };
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void scheduleJob(SchedulerRest schedulerRest) throws SchedulerException {

        JobDetail jobDetail = buildJobDetail(NetworkScanJob.class, schedulerRest);
        logger.info("job details created for schedulers id ->{}", schedulerRest.getId());

        Trigger trigger = buildTrigger(NetworkScanJob.class, schedulerRest);
        logger.info("trigger created for schedulers id ->{}", schedulerRest.getId());
        scheduler.scheduleJob(jobDetail, trigger);
        logger.info("scheduler created for schedulers id ->{}", schedulerRest.getId());


    }


}
