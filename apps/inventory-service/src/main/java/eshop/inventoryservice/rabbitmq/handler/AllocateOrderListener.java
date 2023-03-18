package eshop.inventoryservice.rabbitmq.handler;

import eshop.api.exceptions.NotFoundException;
import eshop.constants.RabbitMQConstants;
import eshop.inventoryservice.entities.ProductInventory;
import eshop.inventoryservice.rabbitmq.event.AllocateOrderRequest;
import eshop.inventoryservice.rabbitmq.event.AllocateOrderResponse;
import eshop.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllocateOrderListener {
    private final InventoryRepository inventoryRepository;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConstants.ALLOCATE_ORDER_QUEUE)
    public void listen(@Payload AllocateOrderRequest allocateOrderRequest) {
        try {
            if (Math.random() < 0.8) {
                rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME,
                        RabbitMQConstants.ALLOCATE_ORDER_RESPONSE_KEY,
                        AllocateOrderResponse.builder()
                                .orderId(allocateOrderRequest.getOrderId())
                                .isSuccessful(false)
                                .build());
                return;
            }

            allocateOrderRequest.getOrderLines().forEach(orderLine -> {
                ProductInventory productInventory = inventoryRepository.findByProductId(orderLine.getProductId())
                        .orElseThrow(
                                NotFoundException::new);
                productInventory.setQuantityOnHand(productInventory.getQuantityOnHand() - orderLine.getQuantity());
                inventoryRepository.save(productInventory);
            });
            rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME,
                    RabbitMQConstants.ALLOCATE_ORDER_RESPONSE_KEY,
                    AllocateOrderResponse.builder()
                            .orderId(allocateOrderRequest.getOrderId())
                            .isSuccessful(true)
                            .build());
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}