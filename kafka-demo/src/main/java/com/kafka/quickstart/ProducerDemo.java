package com.kafka.quickstart;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemo {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.16.33.52:9092, 172.16.33.52:9093, 172.16.33.52:9094");

        /**
         * 生产者 ACK 机制
         * acks=0：生产者不需要等待任何broker回复收到消息的确认回复，就可以发送下一条，性能最高，但容易丢失消息；
         * acks=1：等到leader将数据写入本地log，不用等待follower写入，就可以发送下一条；
         * acks=-1或all：等到leader和所有follower写入，才可以发送下一条。
         *
         * */
        props.put(ProducerConfig.ACKS_CONFIG, "1");

        // 失败重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, 3);

        // 失败重试间隔
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);

        // 设置本地缓冲区，设置了该配置，消息会先发送到缓冲区
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        // kafka本地线程冲缓冲区批量取数据，批量发送到broker,达到该值后，会发送一次
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

        // 消息进入本地batch后，多久被发送，batch没满，到时间后也会被发送，默认是0，即消息被立即发送
        props.put(ProducerConfig.LINGER_MS_CONFIG, 100);

        // 消息key序列化为字节数组
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // 消息value序列化为字节数组
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 1; i++) {
            // 未指定分区时，根据hash(key)%partitionNum计算落入分区的位置
            ProducerRecord<String, String> record = new ProducerRecord<>("test-repTopic",0, "key-test", "测试消息");
            // 同步发送
            // RecordMetadata recordMetadata = producer.send(record).get();

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(null != exception){
                        System.out.println("发送失败：" + exception.getStackTrace());
                    }

                    if(null != metadata) {
                        System.out.println(metadata);
                    }
                }
            });
        }

        producer.close();

    }
}
