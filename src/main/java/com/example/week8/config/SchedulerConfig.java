package com.example.week8.config;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerConfig {

//    @Scheduled(cron="")
//    @Scheduled(fixedRate=10000) // 단위: ms
//    public void fixedRateScheduler() {
//        System.out.println("나는 작업이 끝날때 까지 기다리지 않고 1000ms 마다 실핼될거야");
//    }
}
