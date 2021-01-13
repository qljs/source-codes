package com.rabbitmq.simplest;

import com.rabbitmq.client.*;
import com.rabbitmq.util.RabbitMQUitl;

import java.io.IOException;

public class SimpaleConsumer {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUitl.getConnection();
        Channel channel = connection.createChannel();
        String queueName = "simpaleQuene";
        /**
         * 声明队列
         * queue: 队列
         * durable：是否持久化，true:是；false:否
         * exclusive：是否是独占队列，其他消费者是否可以访问，true:是；false:否
         * autoDelete：消费完之后是否自动删除
         * arguments：其他一些结构化的参数
         *
         */
        channel.queueDeclare("simpaleQuene", true, false, true, null);

        // 绑定消费者
        while (true) {
            channel.basicConsume("simpaleQuene", new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body));
                }
            });
        }
    }
}
