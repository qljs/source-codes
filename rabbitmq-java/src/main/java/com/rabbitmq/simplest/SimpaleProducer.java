package com.rabbitmq.simplest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.RabbitMQUitl;

import java.io.IOException;

public class SimpaleProducer {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUitl.getConnection();
        Channel channel = connection.createChannel();
        for (int i = 0; i < 10; i++) {
            String message = "this is " + i + " message";
            channel.basicPublish("","simpaleQuene", null, message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
