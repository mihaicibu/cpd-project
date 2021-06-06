package rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @RabbitListener(queues = "topic1")
    public String receiveMessageTopicOne(final String message) {
        System.out.println("Received message from topic 1: " + message);
        return message;
    }

    @RabbitListener(queues = "topic2")
    public String receiveMessageTopicTwo(final String message) {
        System.out.println("Received message from topic 2: " + message);
        return message;
    }

    @RabbitListener(queues = "topic3")
    public String receiveMessageTopicThree(final String message) {
        System.out.println("Received message from topic 3: " + message);
        return message;
    }

}
