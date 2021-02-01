package com.rabbitmq.callback;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ReturnCallBack implements RabbitTemplate.ReturnsCallback {


    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.out.println("路由失败：" + returned);
    }
}
