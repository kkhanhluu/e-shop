package eshop.orderservice.rabbitmq.handlers;

import eshop.constants.RabbitMQConstants;
import eshop.orderservice.order.command.OrderCommandService;
import eshop.orderservice.order.command.commands.AllocateOrderFailedCommand;
import eshop.orderservice.order.command.commands.AllocateOrderSuccessCommand;
import eshop.orderservice.order.command.commands.PayOrderFailedCommand;
import eshop.orderservice.order.command.commands.PayOrderSuccessCommand;
import eshop.orderservice.rabbitmq.events.AllocateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AllocateOrderResponseListener {
    private OrderCommandService orderCommandService;

    @RabbitListener(queues = RabbitMQConstants.ALLOCATE_ORDER_RESPONSE_QUEUE)
    public void listen(@Payload AllocateOrderResponse allocateOrderResponse) {
        try {
            final UUID orderId = allocateOrderResponse.getOrderId();
            if (allocateOrderResponse.isSuccessful()) {
                orderCommandService.handle(new AllocateOrderSuccessCommand(orderId));
            } else {
                orderCommandService.handle(new AllocateOrderFailedCommand(orderId));
            }
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }
}