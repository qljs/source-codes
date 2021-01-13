package com.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.RabbitMQUitl;

import java.io.IOException;

public class FanoutProducer {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUitl.getConnection();
        Channel channel = connection.createChannel();
        /**
         * exchange：交换机名称
         * type：类型；fanout：扇形交换机
         */
        channel.exchangeDeclare("fanout_exchanges", "fanout");

        String message = "this is fanout message";
        channel.basicPublish("fanout_exchanges","",null, message.getBytes());

        channel.close();
        connection.close();
    }
}
