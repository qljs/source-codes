package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

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

    @Bean
    public TopicExchange deadLetterExchange(){
        // 死信队列
        TopicExchange deadLetterExchange = new TopicExchange("deadLetterExchange", true, false);
        return deadLetterExchange;
    }
    // ============= 声明队列 =======
    @Bean
    public Queue topicQueue(){
        Map<String, Object> args = new HashMap<>();
        // 设置消息发送的死信队列
        args.put("x-dead-letter-exchange","deadLetterExchange");
        // 发送队列时路由key
        args.put("x-dead-letter-routing-key","deadLetter");
        // 队列消息过期时间，单位毫秒
        args.put("x-message-ttl",10000);
        Queue queue = new Queue("topicQueue", true, false, false, args);
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
    public Queue redirectQueue(){
        Queue queue = new Queue("redirectQueue", true, false, false);
        return queue;
    }

    @Bean
    public Queue deadQueue(){
        Queue queue = new Queue("deadQueue", true, false, false);
        return queue;
    }


    @Bean
    public Binding topicBind(){
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic");
    }

    @Bean
    public Binding deadBind(){
        return BindingBuilder.bind(deadQueue()).to(deadLetterExchange()).with("deadLetter");
    }


}
