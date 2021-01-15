package rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.StartMain;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.config.RabbtiMqConfig;
import com.rabbitmq.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.Oneway;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbtiMqConfig.class)
public class RabbitMqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void testBind(){
        Binding binding = new Binding("topicQueue", Binding.DestinationType.QUEUE,"my_topicExchange","topic",null);
        amqpAdmin.declareBinding(binding);
    }

    @Test
    public void testSendMess() throws InterruptedException, ExecutionException {
        Binding binding = new Binding("topicQueue", Binding.DestinationType.QUEUE,"my_topicExchange","topic",null);
        amqpAdmin.declareBinding(binding);
        MessageProperties properties = new MessageProperties();
        properties.setMessageId("messageId");

        String mes = "test send mess to topic";
        Message message = new Message(mes.getBytes(), properties);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("setConfirmCallback");

                System.out.println(correlationData);
                System.out.println(ack);
                System.out.println(cause);
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                System.out.println("returnCallback");
                System.out.println(returned.toString());
            }
        });

        CorrelationData data = new CorrelationData();

        rabbitTemplate.send("my_topicExchange", "topic1", message, data);
        Thread.sleep(5000);
        System.out.println("send end");
    }

    @Test
    public void testConsumer(){
        Binding binding = new Binding("topicQueue", Binding.DestinationType.QUEUE,"my_topicExchange","topic",null);
        amqpAdmin.declareBinding(binding);
        while (true){
            Message topicQueue = rabbitTemplate.receive("topicQueue");
            System.out.println(topicQueue.toString());
        }
    }

    @Test
    public void testSendJson() throws Exception {
        Binding binding = new Binding("topicQueue", Binding.DestinationType.QUEUE,"my_topicExchange","topic",null);
        amqpAdmin.declareBinding(binding);

        Order order = new Order();
        order.setOrderNo("orderNo111");
        order.setName("爽肤水");
        order.setPrice(25.4);
        order.setCreateDate(new Date());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(order);

        MessageProperties properties = new MessageProperties();
        properties.setConsumerTag("application/json");
        Message message = new Message(json.getBytes(),properties);

        rabbitTemplate.send("my_topicExchange", "topic", message);
    }

    @Test
    public void testJsonConsumer(){
        Binding binding = new Binding("topicQueue", Binding.DestinationType.QUEUE,"my_topicExchange","topic",null);
        amqpAdmin.declareBinding(binding);

        
        Message receive = rabbitTemplate.receive();

    }

}
