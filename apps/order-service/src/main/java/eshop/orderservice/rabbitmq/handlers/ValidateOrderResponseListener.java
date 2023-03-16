package eshop.orderservice.rabbitmq.handlers;

import eshop.constants.RabbitMQConstants;
import eshop.orderservice.order.command.OrderCommandService;
import eshop.orderservice.order.command.commands.ValidateOrderFailedCommand;
import eshop.orderservice.order.command.commands.ValidateOrderSuccessCommand;
import eshop.orderservice.rabbitmq.events.ValidateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidateOrderResponseListener {
    private final OrderCommandService orderCommandService;

    @RabbitListener(queues = RabbitMQConstants.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(@Payload ValidateOrderResponse validateOrderResponse) {
        System.out.println("validateOrderResponse = " + validateOrderResponse);
        try {
            final UUID orderId = validateOrderResponse.getOrderId();
            if (validateOrderResponse.isValid()) {
                orderCommandService.handle(new ValidateOrderSuccessCommand(orderId));
            } else {
                orderCommandService.handle(new ValidateOrderFailedCommand(orderId));
            }
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}