package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbtiMqConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("172.16.33.52");
        factory.setPort(5672);
        factory.setVirtualHost("my_vhost");
        factory.setUsername("mq");

        factory.setPassword("mq");
        factory.setConnectionTimeout(10000);
        factory.setPublisherReturns(true);
        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return factory;
    }


    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        // RabbitAdmin：管理队列和交换机
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // spring容器启动时加载
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }


    // ============== 声明交换机 ========
    @Bean
    public TopicExchange topicExchange(){
        TopicExchange topicExchange = new TopicExchange("my_topicExchange", true, false);
        return topicExchange;
    }

    @Bean
    public DirectExchange directExchange(){
        DirectExchange directExchange = new DirectExchange("my_directExchange", true, false);
        return directExchange;
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        FanoutExchange fanoutExchange = new FanoutExchange("my_fanoutExchange", true, false);
        return fanoutExchange;
    }

    // ============= 声明队列 =======
    @Bean
    public Queue topicQueue(){
        Queue queue = new Queue("topicQueue", true, false, false);
        return queue;
    }

    @Bean
    public Queue directQueue(){
        Queue queue = new Queue("directQueue", true, false, false);
        return queue;
    }

    @Bean
    public Queue fanoutQueue(){
        Queue queue = new Queue("fanoutQueue", true, false, false);
        return queue;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setReceiveTimeout(50000);
        return rabbitTemplate;
    }
}
