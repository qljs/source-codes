package com.kafka.quickstart;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.33.52:9092");
        // 分组名称
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"con_group");
        // 是否自动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // 自动提交间隔
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        // 与消费者的心跳时间，若发现消费者挂掉，会通过负载均衡重发到其他消费
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);
        // 超过该时间，broker未收到消费者心跳，认为它挂了
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
        // 消费者两次拉取消息间隔超过该值，broker会认为该消费者消费能力弱，将其踢出消费组
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 30000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        String topic = "test-repTopic";
        //consumer.subscribe(Arrays.asList(topicName));
        // 消费指定分区
        //consumer.assign(Arrays.asList(new TopicPartition(topicName, 0)));

        //消息回溯消费
        consumer.assign(Arrays.asList(new TopicPartition(topic, 0)));
        consumer.seekToBeginning(Arrays.asList(new TopicPartition(topic, 0)));
        //指定offset消费
        //consumer.seek(new TopicPartition(topicName, 0), 10);

        while (true) {
            /**
             *  poll() API 是拉取消息的长轮询，主要是判断consumer是否还活着，只要我们持续调用poll()，
             *  消费者就会存活在自己所在的group中，并且持续的消费指定partition的消息。
             *  底层是这么做的：消费者向server持续发送心跳，如果一个时间段（session.timeout.ms）
             *  consumer挂掉或是不能发送心跳，这个消费者会被认为是挂掉了，
             *  这个Partition也会被重新分配给其他consumer
             * */
            ConsumerRecords<String, String> records = consumer.poll(Integer.MAX_VALUE);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("收到消息：offset = %d, key = %s, value = %s%n", record.offset(), record.key(),
                        record.value());
            }
            if (records.count() > 0) {
                // 提交offst
                consumer.commitSync();
            }
        }
    }
}
