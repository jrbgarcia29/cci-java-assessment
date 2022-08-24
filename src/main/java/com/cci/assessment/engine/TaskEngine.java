package com.cci.assessment.engine;

import com.cci.assessment.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class TaskEngine {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    TaskService taskService;

    @Scheduled(fixedRateString = "${task.update.rate.in.milliseconds}")
    public void scheduleTaskUpdate() {
        log.info("Start scheduleTaskUpdate : update tasks with Pending status to Done");
        taskService.updateLapsedTasks();
        log.info("Time : " + dateTimeFormatter.format(LocalDateTime.now()));
        log.info("End scheduleTaskUpdate : update tasks with Pending status to Done");
    }
}
