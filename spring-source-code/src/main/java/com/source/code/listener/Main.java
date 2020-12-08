package com.source.code.listener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean.class);
        context.publishEvent(new DemoEvent("test message"));
    }
}
