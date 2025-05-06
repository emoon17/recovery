package com.example.wehago.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5); // 원하는 만큼 설정 가능
        scheduler.setThreadNamePrefix("MyScheduler-");
        scheduler.initialize();

        registrar.setTaskScheduler(scheduler);
        System.out.println("SchedulerConfig - TaskScheduler 강제 등록 완료");
    }
}
