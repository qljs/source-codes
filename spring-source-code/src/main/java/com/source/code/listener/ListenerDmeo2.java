package com.source.code.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ListenerDmeo2 implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if(event instanceof  DemoEvent) {
            System.out.println("=====onApplicationEvent====");

        }
    }
}
