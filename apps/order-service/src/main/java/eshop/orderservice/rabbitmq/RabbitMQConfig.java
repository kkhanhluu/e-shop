package eshop.orderservice.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static eshop.constants.RabbitMQConstants.*;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    private final ObjectMapper objectMapper;

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    /*
    Make payment response queue
    */
    @Bean
    public Queue makePaymentResponseQueue() {
        return new Queue(PAYMENT_RESPONSE_QUEUE);
    }
    @Bean
    public Binding bindingMakePaymentResponseQueue() {
        return BindingBuilder.bind(makePaymentResponseQueue()).to(appExchange()).with(PAYMENT_RESPONSE_KEY);
    }

    /*
        validate order queue
    */
    @Bean
    public Queue validateOrderQueue() {
        return new Queue(VALIDATE_ORDER_QUEUE);
    }
    @Bean
    public Binding bindingValidateOrderQueue() {
        return BindingBuilder.bind(validateOrderQueue()).to(appExchange()).with(VALIDATE_ORDER_KEY);
    }

    /*
        validate order response queue
    */
    @Bean
    public Queue validateOrderResponseQueue() {
        return new Queue(VALIDATE_ORDER_RESPONSE_QUEUE);
    }
    @Bean
    public Binding bindingValidateOrderResponseQueue() {
        return BindingBuilder.bind(validateOrderResponseQueue()).to(appExchange()).with(VALIDATE_ORDER_RESPONSE_KEY);
    }

    /*
       allocate order queue
   */
    @Bean
    public Queue allocateOrderQueue() {
        return new Queue(ALLOCATE_ORDER_QUEUE);
    }
    @Bean
    public Binding bindingAllocateOrderQueue() {
        return BindingBuilder.bind(allocateOrderQueue()).to(appExchange()).with(ALLOCATE_ORDER_KEY);
    }

    /*
        allocate order response queue
    */
    @Bean
    public Queue allocateOrderResponseQueue() {
        return new Queue(ALLOCATE_ORDER_RESPONSE_QUEUE);
    }
    @Bean
    public Binding bindingAllocateOrderResponseQueue() {
        return BindingBuilder.bind(allocateOrderResponseQueue()).to(appExchange()).with(ALLOCATE_ORDER_RESPONSE_KEY);
    }

    /*
        deliver order queue
   */
    @Bean
    public Queue deliverOrderQueue() {
        return new Queue(DELIVER_ORDER_QUEUE);
    }
    @Bean
    public Binding bindingDeliveryOrderQueue() {
        return BindingBuilder.bind(deliverOrderQueue()).to(appExchange()).with(DELIVER_ORDER_KEY);
    }

    /*
       deliver order response queue
   */
    @Bean
    public Queue deliverOrderResponseQueue() {
        return new Queue(DELIVER_ORDER_RESPONSE_QUEUE);
    }
    @Bean
    public Binding bindingDeliverResponseQueue() {
        return BindingBuilder.bind(deliverOrderResponseQueue()).to(appExchange()).with(DELIVER_ORDER_RESPONSE_KEY);
    }
}