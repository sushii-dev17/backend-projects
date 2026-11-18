package com.example.demo.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PagePublishScheduler {

    private final PageService pageService;

    public PagePublishScheduler(PageService pageService){
        this.pageService = pageService;
    }

    @Scheduled(initialDelay = 10000,fixedDelay = 30000)
    public void publishPages(){
        System.out.println("scheduler is triggered............................");
        pageService.publishScheduledPages();
    }

    @Scheduled(initialDelay = 10000,fixedDelay = 30000)
    public void expirePages(){
        System.out.println("expired is triggered............................");
        pageService.makePagesExpiredPages();
    }
}
