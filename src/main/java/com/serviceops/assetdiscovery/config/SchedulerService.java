package com.serviceops.assetdiscovery.config;

import com.serviceops.assetdiscovery.rest.SchedulerRest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.quartz.*;
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

    public static JobDetail buildJobDetail(final Class<? extends Job> jobClass, final SchedulerRest info) {
        final JobDataMap jobDataMap = new JobDataMap();
        Long id = info.getNetworkScanRestId();
        jobDataMap.put("id", id);
        return JobBuilder.newJob(jobClass).withIdentity(String.valueOf(info.getId())).setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final SchedulerRest info) {

        TriggerBuilder<Trigger> cronTriggerTriggerBuilder =
                TriggerBuilder.newTrigger().withIdentity(String.valueOf(info.getId()))
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
            case INTERVAL -> cronTriggerTriggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(
                    "0 */" + convertToMinute(info.getInterval()) + " * ? * *"));
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

    private static String convertToMinute(Long milliseconds) {
        int minutes = (int) (milliseconds / 60000);
        return String.valueOf(minutes);
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void scheduleJob(SchedulerRest schedulerRest) throws SchedulerException {

        JobDetail jobDetail = buildJobDetail(NetworkScanJob.class, schedulerRest);
        logger.info("job details created for schedulers id ->{}", schedulerRest.getId());
        Trigger trigger = buildTrigger(schedulerRest);
        logger.info("trigger created for schedulers id ->{}", schedulerRest.getId());
        scheduler.scheduleJob(jobDetail, trigger);
        logger.info("scheduler created for schedulers id ->{}", schedulerRest.getId());

    }

    public void updateTrigger(final SchedulerRest info) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(String.valueOf(info.getId())));
            if (jobDetail == null) {
                logger.error("Failed to find job with ID -> {}'", info.getId());
                return;
            }
            deleteTimer(String.valueOf(info.getId()));
            scheduler.scheduleJob(jobDetail, buildTrigger(info));
            logger.info("scheduler updated for schedulers id ->{}", info.getId());
        } catch (final SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void deleteTimer(String id) {
        try {
            scheduler.unscheduleJob(TriggerKey.triggerKey(id));
            logger.info("trigger deleted with id -> {}", id);
        } catch (SchedulerException e) {
            logger.error("fail to delete trigger with id -> {}", id);
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


}
