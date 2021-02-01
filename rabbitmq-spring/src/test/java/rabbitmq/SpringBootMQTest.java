package rabbitmq;

import com.rabbitmq.producer.ProducerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerTest.class)
public class SpringBootMQTest {

    @Autowired
    private ProducerTest producerTest;

    @Test
    public void testSend(){
        MessageProperties properties = new MessageProperties();
        properties.setMessageId("messageId");
        producerTest.sendMsg("spring boot test producer", properties);
    }
}
