package com.rabbitmq.fanout;

import com.rabbitmq.client.*;
import com.rabbitmq.util.RabbitMQUitl;

import java.io.IOException;

public class FanoutConsumer {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUitl.getConnection();
        Channel channel = connection.createChannel();
        // 创建一个非持久、排他、自动删除的队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定队列和交换机
        channel.queueBind(queue, "fanout_exchanges","");

        while(true) {
            channel.basicConsume(queue, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body));
                }
            });
        }
    }
}
