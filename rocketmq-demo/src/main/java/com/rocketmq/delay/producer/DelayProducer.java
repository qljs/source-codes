package com.rocketmq.delay.producer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class DelayProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("ays_producer_group");
        producer.setNamesrvAddr("172.16.33.52:9876;172.16.33.217:9876");

        producer.start();

        String msg = "delay message";
        Message message = new Message("TopicTest","delay", msg.getBytes());
        message.setDelayTimeLevel(3);
        SendResult send = producer.send(message);
        System.out.println(send);
        producer.shutdown();

    }
}
