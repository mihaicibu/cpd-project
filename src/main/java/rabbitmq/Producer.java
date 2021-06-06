package rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToTopic(String message, String topic) {
        if(message != null) {
            rabbitTemplate.convertAndSend(RabbitMqApp.EXCHANGE_NAME, RabbitMqApp.ROUTING_KEY, message);
            log.info("Message published: " + message);
        }
    }

}
