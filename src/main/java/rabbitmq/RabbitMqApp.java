package rabbitmq;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
public class RabbitMqApp {
    public static final String EXCHANGE_NAME = "tips_tx";
    public static final String SPECIFIC_PARSING_QUEUE_TOPIC_1 = "topic1";
    public static final String SPECIFIC_PARSING_QUEUE_TOPIC_2 = "topic1";
    public static final String SPECIFIC_PARSING_QUEUE_TOPIC_3 = "topic1";
    public static final String ROUTING_KEY = "tips";

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApp.class, args);
    }

    @Bean
    public TopicExchange activityExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue specificParsingQueue() {
        return new Queue(SPECIFIC_PARSING_QUEUE_TOPIC_1);
    }

    @Bean
    public Binding queueToExchangeBindingSpecific() {
        return BindingBuilder.bind(specificParsingQueue()).to(activityExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
