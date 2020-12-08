package com.source.code.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


public class DemoEvent extends ApplicationEvent {

    private String message;

    public DemoEvent(Object source) {
        super(source);
    }

    public DemoEvent(Object source, String message){
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
