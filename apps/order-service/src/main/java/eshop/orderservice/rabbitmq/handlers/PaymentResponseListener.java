package eshop.orderservice.rabbitmq.handlers;

import eshop.constants.RabbitMQConstants;
import eshop.orderservice.order.command.OrderCommandService;
import eshop.orderservice.order.command.commands.PayOrderFailedCommand;
import eshop.orderservice.order.command.commands.PayOrderSuccessCommand;
import eshop.orderservice.rabbitmq.events.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentResponseListener {
    private final OrderCommandService orderCommandService;
    @RabbitListener(queues = RabbitMQConstants.PAYMENT_RESPONSE_QUEUE)
    public void listen(@Payload PaymentResponse paymentResponse) {
        try {
            final UUID orderId = paymentResponse.getOrderId();
            if (paymentResponse.isSuccessful()) {
                orderCommandService.handle(new PayOrderSuccessCommand(orderId));
            } else {
                orderCommandService.handle(new PayOrderFailedCommand(orderId));
            }
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}